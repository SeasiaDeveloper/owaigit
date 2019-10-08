package com.oway.otto;

public class OnApplyPushNotificationEventTripStart {


    /**
     * type : 1
     * feature : 1
     * id_transaksi : 341
     * status : 5
     * ekl_pelanggan : EKL0098698
     * latitude_start : -7.5453971
     * longitude_start : 112.2417105
     * latitude_end : 7.0707256907261
     * longitude_end : 112.37143334001
     * distance : 1
     * reach_estimate : Time Estimation 18 - 20 Minutes
     * price : 5000
     * order_time : 2019-10-07 18:38:36
     * pickup_address : lamongan
     * destination_address : turi
     * using_balance : 1
     */

    private String type;
    private int feature;
    private int id_transaksi;
    private String status;
    private String ekl_pelanggan;
    private String latitude_start;
    private String longitude_start;
    private String latitude_end;
    private String longitude_end;
    private int distance;
    private String reach_estimate;
    private int price;
    private String order_time;
    private String pickup_address;
    private String destination_address;
    private int using_balance;

    public OnApplyPushNotificationEventTripStart(String type, int feature,int id_transaksi,String status,String ekl_pelanggan,String latitude_start,String longitude_start
    ,String latitude_end,String longitude_end,int distance,String reach_estimate,int price,String order_time,String pickup_address,String destination_address,int using_balance) {
        this.type=type;
        this.feature=feature;
        this.id_transaksi=id_transaksi;
        this.status=status;
        this.ekl_pelanggan=ekl_pelanggan;
        this.latitude_start=latitude_start;
        this.latitude_end=latitude_end;
        this.longitude_start=longitude_start;
        this.longitude_end=longitude_end;
        this.distance=distance;
        this.reach_estimate=reach_estimate;
        this.price=price;
        this.order_time=order_time;
        this.pickup_address=pickup_address;
        this.destination_address=destination_address;
        this.using_balance=using_balance;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFeature() {
        return feature;
    }

    public void setFeature(int feature) {
        this.feature = feature;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEkl_pelanggan() {
        return ekl_pelanggan;
    }

    public void setEkl_pelanggan(String ekl_pelanggan) {
        this.ekl_pelanggan = ekl_pelanggan;
    }

    public String getLatitude_start() {
        return latitude_start;
    }

    public void setLatitude_start(String latitude_start) {
        this.latitude_start = latitude_start;
    }

    public String getLongitude_start() {
        return longitude_start;
    }

    public void setLongitude_start(String longitude_start) {
        this.longitude_start = longitude_start;
    }

    public String getLatitude_end() {
        return latitude_end;
    }

    public void setLatitude_end(String latitude_end) {
        this.latitude_end = latitude_end;
    }

    public String getLongitude_end() {
        return longitude_end;
    }

    public void setLongitude_end(String longitude_end) {
        this.longitude_end = longitude_end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getReach_estimate() {
        return reach_estimate;
    }

    public void setReach_estimate(String reach_estimate) {
        this.reach_estimate = reach_estimate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getPickup_address() {
        return pickup_address;
    }

    public void setPickup_address(String pickup_address) {
        this.pickup_address = pickup_address;
    }

    public String getDestination_address() {
        return destination_address;
    }

    public void setDestination_address(String destination_address) {
        this.destination_address = destination_address;
    }

    public int getUsing_balance() {
        return using_balance;
    }

    public void setUsing_balance(int using_balance) {
        this.using_balance = using_balance;
    }
}
