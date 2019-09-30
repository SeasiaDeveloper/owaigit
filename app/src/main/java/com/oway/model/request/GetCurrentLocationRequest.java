package com.oway.model.request;



public class GetCurrentLocationRequest {


    /**
     * latitude : 30.710673
     * longitude : 76.7091193
     * access_token : beb96008e25901eafc1e74cd53b058b6
     */

    private String latitude;
    private String longitude;
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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
