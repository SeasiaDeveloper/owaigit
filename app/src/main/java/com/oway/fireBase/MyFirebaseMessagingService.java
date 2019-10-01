package com.oway.fireBase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.oway.model.response.PushNotificationResponse;
import com.oway.utillis.ToastUtils;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private String mMessage;
    Map<String, String> data;
    Gson gson;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            gson = new Gson();
            mMessage = remoteMessage.getData().toString();
            data = remoteMessage.getData();
            PushNotificationResponse pushResponse = gson.fromJson(mMessage.toString(), PushNotificationResponse.class);
            ToastUtils.shortToast(mMessage +" "+data.get("rename"));
        }
    }
}