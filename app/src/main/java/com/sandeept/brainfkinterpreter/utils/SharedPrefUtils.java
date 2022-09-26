package com.sandeept.brainfkinterpreter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.sandeept.brainfkinterpreter.R;

public class SharedPrefUtils {

    public static int getIntPreference(String key, int defaultValue, Context context){

        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.settings_pref), Context.MODE_PRIVATE);

        return preferences.getInt(key, defaultValue);
    }

    public static void setIntPreference(String key, int value, Context context){

        SharedPreferences preferences = context.getSharedPreferences(context.getString(R.string.settings_pref), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt(key, value);
        editor.apply();
    }
}
