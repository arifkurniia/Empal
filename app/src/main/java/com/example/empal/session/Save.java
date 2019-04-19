package com.example.empal.session;

import android.content.Context;
import android.content.SharedPreferences;

public class Save {

    public static void Save(Context context, String name, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.empal", Context.MODE_PRIVATE );
        SharedPreferences.Editor edt = sharedPreferences.edit();
        edt.putString(name, value);
        edt.apply();
    }

    public static String Read(Context context, String name, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.empal", Context.MODE_PRIVATE );
        return sharedPreferences.getString(name, defaultValue);
    }
}
