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

package com.oway.utillis;

/**
 * Created by amitshekhar on 08/01/17.
 */

public final class AppConstants {

    static final String DIRECTORY_APP_NAME = "QWAI";
    static final String COMPRESSES_IMAGES = ".COMPRESSES_IMAGES";
    public static final String PREF_NAME = "owai_pref";
    public static final String ADDRESS = "address";
    public static final String SELECT_LATITUDE = "select_latitude";
    public static final String SELECT_LONGITUDE = "select_longitude";

    public static final String TOTURIAL_STATUS = "toturial_status";
    public static final String USER_NAME = "user_name";
    public static final String IMAGE_PATH = "image_path";
    public static final String USER_ID = "user_id";
    public static final String MBR_TOKEN = "mbr_token";
    public static final String SELECTION_GRID = "selection_grid";
    public static final String DATE_FORMAT_DD_MM_YY_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";

    public static final String REGISTER = "/api/customer/register";

    public static final String PICK_UP="Pick_status";
    public static final String DROP_DOWN="Drop_status";

    public static final String LATITUDE="latitude";
    public static final String LONGITUDE="longitude";

    public static final String CANCEL_REASON_ONE="I can't find my driver";
    public static final String CANCEL_REASON_TWO="I have wrong submit destination";
    public static final String CANCEL_REASON_THREE="Driver is too late";

    public static final String BALANCE ="balance";

    public static final String MESSAGE_ID ="message_id";



    public static final int REQUEST_CODE_PICK=1001;
    public static final int REQUEST_CODE_DROP=1002;


    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
