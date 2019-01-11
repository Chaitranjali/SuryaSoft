package com.hello.suryasoftassign.uitility;

import android.app.Application;
import android.content.Context;

public class SuryaSoftApplication extends Application {
    private static SuryaSoftApplication suryaSoftApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        suryaSoftApplication = this;
        AppPreferences.initPreferences(suryaSoftApplication);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    public synchronized static SuryaSoftApplication getInstance() {
        return suryaSoftApplication;
    }

}
