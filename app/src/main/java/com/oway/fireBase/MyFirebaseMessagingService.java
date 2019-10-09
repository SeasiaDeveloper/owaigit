package com.oway.fireBase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.oway.model.response.PushNotificationResponse;
import com.oway.otto.BusProvider;
import com.oway.otto.OnApplyPushNotificationEventArrivingNow;
import com.oway.otto.OnApplyPushNotificationEventArrived;
import com.oway.utillis.CommonUtils;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       /* if (remoteMessage.getData().size() > 0) {
            if(remoteMessage.getData().get("message_id").equals("1"))
            {
                PushNotificationResponse notificationResponse = CommonUtils.getNotificationResponse(remoteMessage.getData());
                BusProvider.getInstance().post(new OnApplyPushNotificationEventArrivingNow(notificationResponse.getType(), notificationResponse.getFeature(), notificationResponse.getId_transaksi(), notificationResponse.getStatus(), notificationResponse.getEkl_driver(), notificationResponse.getDriver_name(), notificationResponse.getDriver_picture(), notificationResponse.getNopol(), notificationResponse.getType_vehicle(), notificationResponse.getVehicle(), notificationResponse.getColor(), notificationResponse.getDriver_rating(), notificationResponse.getReach_estimate(), notificationResponse.getDriver_phone(), notificationResponse.getMessage(), notificationResponse.getMessage_id()));
            }
            else if(remoteMessage.getData().get("message_id").equals("2"))
            {
                PushNotificationResponse notificationResponse = CommonUtils.getNotificationResponse(remoteMessage.getData());
                BusProvider.getInstance().post(new OnApplyPushNotificationEventArrived(notificationResponse.getType(), notificationResponse.getFeature(), notificationResponse.getId_transaksi(), notificationResponse.getStatus(), notificationResponse.getEkl_driver(), notificationResponse.getDriver_name(), notificationResponse.getDriver_picture(), notificationResponse.getNopol(), notificationResponse.getType_vehicle(), notificationResponse.getVehicle(), notificationResponse.getColor(), notificationResponse.getDriver_rating(), notificationResponse.getReach_estimate(), notificationResponse.getDriver_phone(), notificationResponse.getMessage(), notificationResponse.getMessage_id()));
            }
        }*/
    }
}