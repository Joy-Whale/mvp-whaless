package me.whaless.app;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

/**
 * Author: Ji
 * Date:   2019/5/5
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
