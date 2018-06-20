package com.example.ssmbu.gankki;

import android.app.Application;
import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowManager;


public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        FlowManager.init(this);

    }
    public static   Context getContext(){
        return context;
    }
}
