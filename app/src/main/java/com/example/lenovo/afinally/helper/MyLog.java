package com.example.lenovo.afinally.helper;

import android.util.Log;

/**
 * Created by ASUS on 4/25/2017.
 */

public class MyLog {
    static boolean show = true;
    public static void D(String name,String value){
        if(show){
            Log.d(name,value);
        }
    }
}
