package com.oway.fireBase;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.oway.model.response.PushNotificationResponse;
import com.oway.model.response.PushNotificationResponseStart;
import com.oway.otto.BusProvider;
import com.oway.otto.OnApplyPushNotificationEventArrived;
import com.oway.otto.OnApplyPushNotificationEventArrivingNow;
import com.oway.otto.OnApplyPushNotificationEventTripStart;
import com.oway.utillis.AppConstants;
import com.oway.utillis.CommonUtils;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            if (remoteMessage.getData().get(AppConstants.MESSAGE_ID).equals("1")) {
                PushNotificationResponse notificationResponse = CommonUtils.getNotificationResponse(remoteMessage.getData());
                BusProvider.getInstance().post(new OnApplyPushNotificationEventArrivingNow(notificationResponse.getType(), notificationResponse.getFeature(), notificationResponse.getId_transaksi(), notificationResponse.getStatus(), notificationResponse.getEkl_driver(), notificationResponse.getDriver_name(), notificationResponse.getDriver_picture(), notificationResponse.getNopol(), notificationResponse.getType_vehicle(), notificationResponse.getVehicle(), notificationResponse.getColor(), notificationResponse.getDriver_rating(), notificationResponse.getReach_estimate(), notificationResponse.getDriver_phone(), notificationResponse.getMessage(), notificationResponse.getMessage_id()));
            } else if (remoteMessage.getData().get(AppConstants.MESSAGE_ID).equals("2")) {
                PushNotificationResponse notificationResponse = CommonUtils.getNotificationResponse(remoteMessage.getData());
                BusProvider.getInstance().post(new OnApplyPushNotificationEventArrived(notificationResponse.getType(), notificationResponse.getFeature(), notificationResponse.getId_transaksi(), notificationResponse.getStatus(), notificationResponse.getEkl_driver(), notificationResponse.getDriver_name(), notificationResponse.getDriver_picture(), notificationResponse.getNopol(), notificationResponse.getType_vehicle(), notificationResponse.getVehicle(), notificationResponse.getColor(), notificationResponse.getDriver_rating(), notificationResponse.getReach_estimate(), notificationResponse.getDriver_phone(), notificationResponse.getMessage(), notificationResponse.getMessage_id()));
            } else if (remoteMessage.getData().get(AppConstants.MESSAGE_ID).equals("3")) {
                PushNotificationResponseStart notificationResponse = CommonUtils.getNotificationArrivedNowResponse(remoteMessage.getData());
                BusProvider.getInstance().post(new OnApplyPushNotificationEventTripStart(notificationResponse.getType(), notificationResponse.getFeature(), notificationResponse.getId_transaksi(), notificationResponse.getStatus(), notificationResponse.getEkl_pelanggan(), notificationResponse.getLatitude_start(), notificationResponse.getLongitude_start(), notificationResponse.getLatitude_end(), notificationResponse.getLongitude_end(), notificationResponse.getDistance(), notificationResponse.getReach_estimate(), notificationResponse.getPrice(), notificationResponse.getOrder_time(), notificationResponse.getPickup_address(), notificationResponse.getDestination_address(), notificationResponse.getUsing_balance(),notificationResponse.getMessage(),notificationResponse.getMessage_id(),notificationResponse.getDriver_picture(),notificationResponse.getDriver_name(),notificationResponse.getType_vehicle(),notificationResponse.getVehicle()));
            }
        }
    }
}