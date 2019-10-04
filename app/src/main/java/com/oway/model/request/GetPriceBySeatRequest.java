package com.oway.model.request;
public class GetPriceBySeatRequest {

    /**
     * latitude : 30.456777
     * longitude : 34.44444
     * distance : 4
     * access_token : 9e2e4757811535676c21b67417e8f313
     */

    private String latitude;
    private String longitude;
    private String distance;
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

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
