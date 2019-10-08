package com.oway.otto;

public class OnApplyPushNotificationEvent {


    private final String type;
    private final int feature;
    private final int id_transaksi;
    private final String status;
    private final String ekl_driver;
    private final String driver_name;
    private final String driver_picture;
    private final String nopal;
    private final String type_vehicle;
    private final String vehicle;
    private final String color;
    public String getStatus() {
        return status;
    }

    public String getEkl_driver() {
        return ekl_driver;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public String getDriver_picture() {
        return driver_picture;
    }

    public String getNopal() {
        return nopal;
    }

    public String getType_vehicle() {
        return type_vehicle;
    }

    public String getVehicle() {
        return vehicle;
    }

    public String getColor() {
        return color;
    }



    public String getType() {
        return type;
    }

    public int getFeature() {
        return feature;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public OnApplyPushNotificationEvent(String type, int feature, int id_transaksi, String status, String ekl_driver, String driver_name, String driver_picture
            , String nopal, String type_vehicle, String vehicle, String color) {
        this.type = type;
        this.feature = feature;
        this.id_transaksi = id_transaksi;
        this.status = status;
        this.ekl_driver = ekl_driver;
        this.driver_name = driver_name;
        this.driver_picture = driver_picture;
        this.nopal = nopal;
        this.type_vehicle = type_vehicle;
        this.vehicle = vehicle;
        this.color = color;

    }
}
