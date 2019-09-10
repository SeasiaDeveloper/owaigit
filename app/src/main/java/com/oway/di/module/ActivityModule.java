package com.oway.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.oway.di.ActivityContext;
import com.oway.utillis.ValidationUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    ValidationUtils getValidationUtill() {
        return new ValidationUtils(mActivity);
    }

}
