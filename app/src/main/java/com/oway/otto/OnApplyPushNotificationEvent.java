package com.oway.otto;

public class OnApplyPushNotificationEvent {


    private final String type;
    private final String feature;

    public OnApplyPushNotificationEvent(String type, String feature) {
        this.type = type;
        this.feature = feature;

    }

    public String getType() {
        return type;
    }

    public String getFeature() {
        return feature;
    }
}
