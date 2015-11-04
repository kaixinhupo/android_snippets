package cn.lxw.us.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lianxw on 2015/7/31.
 * Preference工具类
 */
public class PreferenceUtils {

    private static final String PREFERENCE_NAME = "default_preference.xml";

    public static SharedPreferences getPreference(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static String getPreferenceString(Context context, String key) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getString(key,null);
    }

    public static Boolean getPreferenceBoolean(Context context,String key) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    public static SharedPreferences.Editor getPreferenceEditor(Context context) {
        return context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
    }

    public static String getPrivilegeString(Context context) {
        return getPrivilegeString(context,false);
    }

    public static String getPrivilegeString(Context context,boolean withStore) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        if(!sp.getBoolean(Constants.PREF_LOGIN,false)) {
            return  null;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("from=mobile");
        String accessKey = sp.getString(Constants.PREF_ACCESS_KEY,null);
        if (accessKey!=null) {
            builder.append("&access_key=").append(accessKey);
        }
        String uid = sp.getString(Constants.PREF_UID,null);
        if (uid!=null) {
            builder.append("&uid=").append(uid);
        }
        if (withStore) {
            String storeId = sp.getString(Constants.PREF_STORE_ID,null);
            if (storeId!=null) {
                builder.append("&store_id=").append(storeId);
            }
        }
        return builder.toString();
    }
}
