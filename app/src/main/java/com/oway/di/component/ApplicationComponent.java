package com.oway.di.component;

import android.app.Application;
import android.content.Context;

import com.oway.App;
import com.oway.datasource.implementation.ApiService;
import com.oway.datasource.pref.PreferencesHelper;
import com.oway.di.ApplicationContext;
import com.oway.di.module.ApplicationModule;
import javax.inject.Singleton;
import dagger.Component;
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    ApiService apisservice();

    PreferencesHelper prefHelper();

    void inject(App app);

}
