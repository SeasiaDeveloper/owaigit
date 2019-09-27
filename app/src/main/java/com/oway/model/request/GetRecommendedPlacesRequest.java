package com.oway.model.request;



public class GetRecommendedPlacesRequest {


    /**
     * latitude : 30.7046
     * longitude : 76.7179
     * ekl_customer : EKL0098679
     * access_token : 26e21c73006aeb914f4e43d52e2d9578
     */

    private String latitude;
    private String longitude;
    private String ekl_customer;
    private String access_token;

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
