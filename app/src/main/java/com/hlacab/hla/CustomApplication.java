package com.hlacab.hla;

import android.app.Application;

import com.teliver.sdk.core.Teliver;

/**
 * Created by gopinath on 16/12/17.
 */

public class CustomApplication extends Application {
    public void onCreate()
    {
        super.onCreate();
        Teliver.init(this,"f090dc812d7ac369d89d8a7fda7f8331");
    }
}
