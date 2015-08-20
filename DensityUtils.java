package cn.lxw.scenedeer.utils;

import android.content.res.Resources;

/**
 * Created by Lianxw on 2015/8/20.
 *
 */
public class DensityUtils {

    private static float DENSITY;

    static {
        DENSITY = Resources.getSystem().getDisplayMetrics().density;
    }

    public static int dp2px(float dp) {
        return (int) (DENSITY * dp + 0.5f);
    }

    public static float px2dp(int px) {
        return (int) (px / DENSITY + 0.5f);
    }
}
