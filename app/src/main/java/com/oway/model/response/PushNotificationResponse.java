package com.oway.model.response;

public class PushNotificationResponse {


    /**
     * type : 1
     * feature : 1
     * id_transaksi : 12
     * status : 2
     * ekl_driver : EKLXXXX
     * driver_name : Suko
     * driver_picture : https://foto.jpg
     * nopol : L 2200 S
     * type_vehicle : matic
     * vehicle : Honda Supra x
     * color : red
     */

    private String type;
    private String feature;
    private String id_transaksi;
    private String status;
    private String ekl_driver;
    private String driver_name;
    private String driver_picture;
    private String nopol;
    private String type_vehicle;
    private String vehicle;
    private String color;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEkl_driver() {
        return ekl_driver;
    }

    public void setEkl_driver(String ekl_driver) {
        this.ekl_driver = ekl_driver;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_picture() {
        return driver_picture;
    }

    public void setDriver_picture(String driver_picture) {
        this.driver_picture = driver_picture;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getType_vehicle() {
        return type_vehicle;
    }

    public void setType_vehicle(String type_vehicle) {
        this.type_vehicle = type_vehicle;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}