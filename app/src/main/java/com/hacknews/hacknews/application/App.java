package com.hacknews.hacknews.application;

import android.app.Application;

import com.hacknews.hacknews.models.MyObjectBox;

import io.objectbox.BoxStore;

/**
 * Created by Eshan on 10/18/17.
 */


public class App extends Application
{

    private BoxStore boxStore;
    @Override
    public void onCreate()
    {
        super.onCreate();
        boxStore = MyObjectBox.builder().androidContext(App.this).build();
    }

    public BoxStore getBoxStore() {
        return boxStore;

    }


}
