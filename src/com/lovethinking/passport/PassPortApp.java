package com.lovethinking.passport;

import com.lovethinking.passport.factory.DbFactory;

import android.app.Application;

public class PassPortApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        DbFactory.instance().createTable(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
