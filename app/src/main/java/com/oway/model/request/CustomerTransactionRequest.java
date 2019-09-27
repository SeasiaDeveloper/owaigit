package com.oway.model.request;



public class CustomerTransactionRequest {


    /**
     * ekl_customer : EKL0098679
     * order_fitur : 1
     * start_latitude : -7.5453971
     * start_longitude : 112.2417105
     * end_latitude : 7.0707256907261
     * end_longitude : 112.37143334001
     * distance : 1
     * price : 2000
     * order_time : 2019-08-08 10:16:22
     * pickup_address : lamongan
     * destination_address : turi
     * final_price : 2000
     * use_balance : 0
     * seat : 4
     * access_token : 7eb2ba0e1da94835e8b1677536b25e4e
     */

    private String ekl_customer;
    private String order_fitur;
    private double start_latitude;
    private double start_longitude;
    private double end_latitude;
    private double end_longitude;
    private String distance;
    private String price;
    private String order_time;
    private String pickup_address;
    private String destination_address;
    private String final_price;
    private String use_balance;
    private int seat;
    private String access_token;

    public String getEkl_customer() {
        return ekl_customer;
    }

    public void setEkl_customer(String ekl_customer) {
        this.ekl_customer = ekl_customer;
    }

    public String getOrder_fitur() {
        return order_fitur;
    }

    public void setOrder_fitur(String order_fitur) {
        this.order_fitur = order_fitur;
    }

    public double getStart_latitude() {
        return start_latitude;
    }

    public void setStart_latitude(double start_latitude) {
        this.start_latitude = start_latitude;
    }

    public double getStart_longitude() {
        return start_longitude;
    }

    public void setStart_longitude(double start_longitude) {
        this.start_longitude = start_longitude;
    }

    public double getEnd_latitude() {
        return end_latitude;
    }

    public void setEnd_latitude(double end_latitude) {
        this.end_latitude = end_latitude;
    }

    public double getEnd_longitude() {
        return end_longitude;
    }

    public void setEnd_longitude(double end_longitude) {
        this.end_longitude = end_longitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getUse_balance() {
        return use_balance;
    }

    public void setUse_balance(String use_balance) {
        this.use_balance = use_balance;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
