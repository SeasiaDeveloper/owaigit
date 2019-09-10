/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.oway.datasource.pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.oway.di.ApplicationContext;
import com.oway.di.PreferenceInfo;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Created by janisharali on 27/01/17.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    public static final String PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE";
    public static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    public static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    public static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    public static final String PREF_KEY_CURRENT_USER_PROFILE_PIC_URL
            = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL";

    public static final String PREF_KEY_QUICK_SESSION_ID = "PREF_KEY_QUICK_SESSION_ID";
    public static final String PREF_KEY_CARD_HOLDER_KEY = "PREF_KEY_CARD_HOLDER_KEY";
    public static final String PREF_KEY_LASTNAME = "PREF_KEY_LASTNAME";
    public static final String PREF_KEY_FIRST_NAME = "PREF_KEY_FIRST_NAME";
    public static final String PREF_KEY_GENDER = "PREF_KEY_GENDER";
    public static final String PREF_KEY_DOB = "PREF_KEY_DOB";
    public static final String PREF_KEY_ROLE = "PREF_KEY_ROLE";
    public static final String PREF_KEY_STUDENT_START_DATE = "PREF_KEY_STUDENT_START_DATE";
    public static final String PREF_KEY_STUDENT_END_DATE = "PREF_KEY_STUDENT_END_DATE";
    public static final String PREF_KEY_ADULT_DEPENDENT = "PREF_KEY_ADULT_DEPENDENT";
    public static final String PREF_KEY_STATUS = "PREF_KEY_STATUS";
    public static final String PREF_KEY_USER_CODE = "PREF_KEY_USER_CODE";
    public static final String PREF_KEY_COMPANY_KEY = "PREF_KEY_COMPANY_KEY";
    public static final String PREF_KEY_COMPANY_NAME = "PREF_KEY_COMPANY_NAME";
    public static final String PREF_KEY_YEAR_START = "PREF_KEY_YEAR_START";
    public static final String PREF_KEY_YEAR_END = "PREF_KEY_YEAR_END";

    public static final String PREF_KEY_POSTAL_CODE = "PREF_KEY_POSTAL_CODE";
    public static final String PREF_KEY_FAX_NUM = "PREF_KEY_FAX_NUM";
    public static final String PREF_KEY_CARD_NUM = "PREF_KEY_CARD_NUM";
    public static final String PREF_KEY_CITY = "PREF_KEY_CITY";
    public static final String PREF_KEY_CARD_ADD1 = "PREF_KEY_CARD_ADD1";
    public static final String PREF_KEY_CARD_EMAIL = "PREF_KEY_CARD_EMAIL";
    public static final String PREF_KEY_CARD_ADD2 = "PREF_KEY_CARD_ADD2";
    public static final String PREF_KEY_CARD_PHONE_NUM = "PREF_KEY_CARD_PHONE_NUM";
    public static final String PREF_KEY_CARD_PROVINCE = "PREF_KEY_CARD_PROVINCE";
    public static final String PREF_KEY_PROFILE_UPDATED_TIME = "PREF_KEY_PROFILE_UPDATED_TIME";
    public static final String PREF_KEY_CARDHOLDER_NO = "PREF_KEY_CARDHOLDER_NO";
    public static final String EMAIL = "EMAIL";

    public static final String PREF_KEY_USER_PASSWORD = "PREF_KEY_USER_PASSWORD";

    public static final String PREF_KEY_ACCOUNT_NUMBER = "PREF_KEY_ACCOUNT_NUMBER";
    public static final String PREF_KEY_PROFILE_PATH = "PREF_KEY_PROFILE_PATH";
    public static final String PREF_KEY_BRANCH_NUMBER = "PREF_KEY_BRANCH_NUMBER";
    public static final String PREF_KEY_INSTITUTE_NUMBER = "PREF_KEY_INSTITUTE_NUMBER";
    public static final String PREF_KEY_IS_TEMP_PASSWORD = "PREF_KEY_IS_TEMP_PASSWORD";

    public static final String PREF_KEY_FINGERPRINT_MSG_SHOWN_ONCE = "PREF_KEY_FINGERPRINT_MSG_SHOWN_ONCE";
    public static final String PREF_KEY_ENABLE_FINGERPRINT = "PREF_KEY_ENABLE_FINGERPRINT";
    private static final String PREF_KEY_COMPLICATED_PLAN_IND = "PREF_KEY_COMPLICATED_PLAN_IND";

    public final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }


    @Override
    public void writeString(String key, String val) {
        mPrefs.edit().putString(key, val).apply();
    }

    @Override
    public String readString(String key, String defaultVal) {
        return mPrefs.getString(key, defaultVal);
    }

    @Override
    public void writeBoolean(String key, boolean val) {
        mPrefs.edit().putBoolean(key, val).apply();
    }

    @Override
    public boolean readBoolean(String key, boolean defaultVal) {
        return mPrefs.getBoolean(key, defaultVal);
    }

    @Override
    public void writeLong(String key, Long val) {
        mPrefs.edit().putLong(key, val).apply();
    }

    @Override
    public Long readLong(String key, Long defaultVal) {
        return mPrefs.getLong(key, defaultVal);
    }






    @Override
    public void addPrefChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        mPrefs.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override
    public void removePrefChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        mPrefs.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }


}
