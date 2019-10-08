package com.oway.otto;

public class OnApplyPushNotificationEventArrived {


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
    private final double rating;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    private String message_id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public String getDriver_phone() {
        return driver_phone;
    }

    public void setDriver_phone(String driver_phone) {
        this.driver_phone = driver_phone;
    }

    private String driver_phone;

    public double getRating() {
        return rating;
    }

    public String getReach_estimate() {
        return reach_estimate;
    }

    private final String reach_estimate;
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

    public OnApplyPushNotificationEventArrived(String type, int feature, int id_transaksi, String status, String ekl_driver, String driver_name, String driver_picture
            , String nopal, String type_vehicle, String vehicle, String color, double rating, String reach_estimate, String driver_phone, String message, String message_id) {
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
        this.rating=rating;
        this.reach_estimate=reach_estimate;
        this.driver_phone=driver_phone;
        this.message=message;
        this.message_id=message_id;
    }
}
