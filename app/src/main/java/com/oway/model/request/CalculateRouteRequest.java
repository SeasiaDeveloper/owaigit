package com.oway.model.request;



public class CalculateRouteRequest {


    /**
     * lat1 : -7.117326
     * lon1 : 112.416489
     * lat2 : -7.119326
     * lon2 : 112.416789
     * access_token : 4c0e338a39ec700123a15b5499b4331c
     */

    private String lat1;
    private String lon1;
    private String lat2;
    private String lon2;
    private String access_token;

    public String getLat1() {
        return lat1;
    }

    public void setLat1(String lat1) {
        this.lat1 = lat1;
    }

    public String getLon1() {
        return lon1;
    }

    public void setLon1(String lon1) {
        this.lon1 = lon1;
    }

    public String getLat2() {
        return lat2;
    }

    public void setLat2(String lat2) {
        this.lat2 = lat2;
    }

    public String getLon2() {
        return lon2;
    }

    public void setLon2(String lon2) {
        this.lon2 = lon2;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
