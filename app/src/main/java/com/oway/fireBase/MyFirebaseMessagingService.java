package com.oway.fireBase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.oway.model.response.PushNotificationResponse;
import com.oway.otto.BusProvider;
import com.oway.otto.OnApplyPushNotificationEvent;
import com.oway.utillis.CommonUtils;
import com.oway.utillis.ConstsCore;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            PushNotificationResponse notificationResponse = CommonUtils.getNotificationResponse(remoteMessage.getData());
            BusProvider.getInstance().post(new OnApplyPushNotificationEvent(notificationResponse.getType(), notificationResponse.getFeature(),notificationResponse.getId_transaksi(),notificationResponse.getStatus(),notificationResponse.getEkl_driver(),notificationResponse.getDriver_name(),notificationResponse.getDriver_picture(),notificationResponse.getNopol(),notificationResponse.getType_vehicle(),notificationResponse.getVehicle(),notificationResponse.getColor()));
        }
    }
}