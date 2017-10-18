package com.hacknews.hacknews.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Eshan on 10/18/17.
 */

public class SharedPref
{
    private static SharedPref sharedPreferences;
    private static SharedPreferences mPref;
    private static SharedPreferences.Editor editor;
    private static final String MyPREFERENCES = "MY_PREF";
    private static final String NEWS_ID = "NEWS_ID";




    public static SharedPref getInstance(Context context){
        if(sharedPreferences == null){
            sharedPreferences = new SharedPref();
            mPref = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            editor = mPref.edit();
        }
        return sharedPreferences;
    }

    private SharedPref(){

    }

    public void saveNewsId(long newsId)
    {
        editor.putLong(NEWS_ID, newsId);
        editor.commit();
    }

    public long getNewsId()
    {
        return mPref.getLong(NEWS_ID, 0);
    }



}