package cn.lxw.us.network;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import cn.lxw.us.models.JsonMessage;
import cn.lxw.us.utils.NetworkUtils;

/**
 * Created by Lianxw on 2015/7/22.
 * 访问API基础类
 */
public abstract class JsonApi<T extends JsonMessage> {

    public static final String API_HOST ="http://192.168.199.228/api/";
    public static final int METHOD_GET = 0;
    public static final int METHOD_POST = 1;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final long CONNECTION_TIMEOUT = 10;
    private static final long READ_TIMEOUT = 30;
    private int method;
    private String url;
    private OkHttpClient client;

    public JsonApi(String url) {
        this(url, METHOD_GET);
    }

    public JsonApi(String url, int method) {
        this.url = url;
        this.method = method;
        this.client = new OkHttpClient();
        this.client.setConnectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        this.client.setReadTimeout(READ_TIMEOUT,TimeUnit.SECONDS);
    }

    public T call() {
        prepareUrl();
        String json = this.method == METHOD_GET?get():post();
        if(json==null) {
            return null;
        }
        T rst = null;
        try {
            rst = parse(json);
        } catch (Exception e) {
            Log.e("JsonApi","parse json error:"+e.getMessage());
            e.printStackTrace();
        }
        return rst;
    }

    private void prepareUrl() {
        int questionMark = url.indexOf('?');
        String address;
        String queryString;
        if (questionMark > 0) {
            address = url.substring(0, questionMark);
            queryString = url.substring(questionMark)+"&";
        } else {
            address = url;
            queryString = "?";
        }
        String params = getParams();
        if (params != null) {
            queryString += params;
        }
        if(queryString.length()>1) {
            this.url = address + queryString;
        }
    }

    private String get() {
        Request request = new Request.Builder()
                .url(url)
                .build();
        String json = null;
        try {
            Response response = client.newCall(request).execute();
            json = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    private String post() {
        String jsonBody = getBody();
        if (jsonBody==null){
            return null;
        }
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        String json = null;
        try {
            Response response = client.newCall(request).execute();
            json = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    protected abstract String getParams();

    protected abstract String getBody();

    protected abstract T parse(String json);
}
