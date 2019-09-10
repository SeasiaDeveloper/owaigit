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


import android.content.SharedPreferences;


/**
 * Created by janisharali on 27/01/17.
 */

public interface PreferencesHelper {

    void writeString(String key, String val);

    String readString(String key, String defaultVal);

    void writeBoolean(String key, boolean val);

    boolean readBoolean(String key, boolean defaultVal);

    void writeLong(String key, Long val);

    Long readLong(String key, Long defaultVal);

    void addPrefChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);

    void removePrefChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener);


}
