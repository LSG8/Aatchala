package com.example.aatchala;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class Session {

    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        //prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
        prefs = cntx.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public static void createLoginSession(String userId) {
        editor.putBoolean("IsLogin", true);
        editor.putString("userId", userId);
        editor.commit();
    }

    public static boolean checkLogin() {
        if (prefs.getBoolean("IsLogin", true))
            return true;
        else return false;

    }


    public static String getUserId() {
        return prefs.getString("userId", "");
    }

    public static void createLogoutSession() {
        editor.putBoolean("IsLogin", false);
        editor.clear();
        editor.commit();
    }

}
