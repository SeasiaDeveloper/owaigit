package com.oway.model.request;



public class GetEstimateBikeRequest {


    /**
     * distance : 2
     * id_fitur : 4
     * access_token :
     */

    private String distance;
    private String id_fitur;
    private String access_token;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getId_fitur() {
        return id_fitur;
    }

    public void setId_fitur(String id_fitur) {
        this.id_fitur = id_fitur;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
