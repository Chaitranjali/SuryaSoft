package com.hello.assignment.uitility;

import android.app.Application;
import android.content.Context;

public class AssignmentApplication extends Application {
    private static AssignmentApplication assignmentApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        assignmentApplication = this;
        AppPreferences.initPreferences(this);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    public synchronized static AssignmentApplication getInstance() {
        return assignmentApplication;
    }

}
