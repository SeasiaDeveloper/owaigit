package com.oway.di.component;


import com.oway.di.PerActivity;
import com.oway.di.module.ActivityModule;
import com.oway.ui.login.LoginActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);

}
