package cn.lxw.us.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lianxw on 2015/7/22.
 * 转换后的Json消息
 */
public class JsonMessage {

    private int code;
    @SerializedName("msg")
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
