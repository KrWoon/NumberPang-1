package com.anjinma.numberpang;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 박지운 on 2017-06-05.
 */

public class PreferenceUtil {

    public static void setPreferences(Context context, String key, Boolean value) {
        SharedPreferences p = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = p.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static Boolean getPreferences(Context context, String key) {
        SharedPreferences p = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        p = context.getSharedPreferences("pref", context.MODE_PRIVATE);
        return p.getBoolean(key, false);
    }
}
