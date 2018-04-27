package com.example.kien.projecttwitsplit.utils;

import android.app.Application;

public class AppController extends Application {

    public static final String SAVE_USER_NAME = "username";
    public static final String SAVE_PASSWORD = "password";
    public static final String SAVE_UID = "uid";

    //Creatting class object
    private static AppController mInstance;

    private SharedPrefUtil pref;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    //Public static method to get the instance of this class
    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public SharedPrefUtil getPrefManager() {
        if (pref == null) {
            pref = new SharedPrefUtil(this);
        }

        return pref;
    }
}
