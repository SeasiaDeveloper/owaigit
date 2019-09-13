package com.oway.di.component;


import com.oway.di.PerActivity;
import com.oway.di.module.ActivityModule;
import com.oway.ui.home.MainActivity;
import com.oway.ui.home.Profile.ProfileFragment;
import com.oway.ui.home.RequisiteAndCertain.RequisiteAndCertainFragment;
import com.oway.ui.home.dashboard.DashBoardFragment;
import com.oway.ui.login.LoginActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(DashBoardFragment dashBoardFragment);

    void inject(ProfileFragment profileFragment);

    void inject(RequisiteAndCertainFragment requisiteAndCertainFragment);

}
