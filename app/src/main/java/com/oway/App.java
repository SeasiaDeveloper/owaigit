package com.oway;

import android.app.Application;
import android.os.StrictMode;

import com.oway.datasource.implementation.ApiService;
import com.oway.di.component.ApplicationComponent;
import com.oway.di.component.DaggerApplicationComponent;
import com.oway.di.module.ApplicationModule;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class App extends Application {

    private static App app;
    private ApplicationComponent mApplicationComponent;
    @Inject
    ApiService apiService;

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .build();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
        this.app = this;

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public static App getInstance() {
        return app;
    }

}