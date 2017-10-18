package com.hacknews.hacknews.util;

import android.util.Log;

/**
 * Created by Eshan on 10/18/17.
 */

public class Logger
{
    private static  boolean isLogEnabled = true;



    public static void e(String tag, String msg, Throwable th) {
        if(!isLogEnabled){
            return;
        }
        Log.e(tag, msg, th);
    }

    public static void w(String tag, String msg, Throwable th) {
        if(!isLogEnabled){
            return;
        }
        Log.w(tag, msg, th);
    }

    public static void i(String tag, String msg, Throwable th) {
        if(!isLogEnabled){
            return;
        }
        Log.i(tag, msg, th);
    }

    public static void i(String tag, String msg) {
        if(!isLogEnabled){
            return;
        }
        Log.i(tag, msg, null);
    }

    public static void d(String tag, String msg, Throwable th) {
        if(!isLogEnabled){
            return;
        }
        Log.d(tag, msg);
    }

    public static void d(String tag, String msg) {
        if(!isLogEnabled){
            return;
        }
        Log.d(tag, msg);
    }
    public static void e(String tag, String msg) {
        if(!isLogEnabled){
            return;
        }
        Log.e(tag, msg);
    }


    public static void show(int loginfo, String tag, String message){
        if(isLogEnabled) {
            switch (loginfo) {
                case Log.INFO:
                    Log.i(tag, message);
                    break;
                case Log.DEBUG:
                    Log.i(tag, message);
                    break;
                case Log.WARN:
                    Log.i(tag, message);
                    break;
                case Log.ERROR:
                    Log.i(tag, message);
                    break;
                case Log.VERBOSE:
                    Log.i(tag, message);
                    break;
                default:
                    Log.e(tag, message);
                    break;
            }
        }

    }
}
