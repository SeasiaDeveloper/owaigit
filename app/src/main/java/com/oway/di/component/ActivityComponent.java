package com.oway.di.component;


import com.oway.di.PerActivity;
import com.oway.di.module.ActivityModule;
import com.oway.ui.home.MainActivity;
import com.oway.ui.home.Profile.ProfileFragment;
import com.oway.ui.home.RequisiteAndCertain.RequisiteAndCertainFragment;
import com.oway.ui.home.dashboard.DashBoardFragment;
import com.oway.ui.login.LoginActivity;
import com.oway.ui.login.WelcomeScreenActivity;
import com.oway.ui.registration.Registration;
import com.oway.ui.registration.RegistrationViewThree;
import com.oway.ui.trip.MotorTripActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = com.oway.di.component.ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    void inject(DashBoardFragment dashBoardFragment);

    void inject(ProfileFragment profileFragment);

    void inject(RequisiteAndCertainFragment requisiteAndCertainFragment);

    void inject(RegistrationViewThree threeFragemnt);

    void inject(WelcomeScreenActivity welcomeScreenActivity);

    void inject(Registration register);

    void inject(MotorTripActivity motorTripActivity);

}
