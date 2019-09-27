package com.oway.model.request;



public class GetNearestDriverRequest {


    /**
     * latitude : -7.5452439
     * longitude : 112.7064384
     * order_fitur : 1
     * ekl_customer : EKL0098679
     * access_token : c995847b065a13f82528bf4670b3653d
     */

    private String latitude;
    private String longitude;
    private String order_feature;
    private String ekl_customer;
    private String access_token;

    public String getOrder_feature() {
        return order_feature;
    }

    public void setOrder_feature(String order_feature) {
        this.order_feature = order_feature;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEkl_customer() {
        return ekl_customer;
    }

    public void setEkl_customer(String ekl_customer) {
        this.ekl_customer = ekl_customer;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
