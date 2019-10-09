package com.oway.model.response;

public class CalculateRouteResponse {


    /**
     * distance : 456
     * trafficTime : 111
     * baseTime : 106
     * text : The trip takes <span class="length">456 m</span> and <span class="time">2 mins</span>.
     * travelTime : 106
     * _type : RouteSummaryType
     */

    private int distance;
    private int trafficTime;
    private int baseTime;
    private String text;
    private int travelTime;
    private String _type;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTrafficTime() {
        return trafficTime;
    }

    public void setTrafficTime(int trafficTime) {
        this.trafficTime = trafficTime;
    }

    public int getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(int baseTime) {
        this.baseTime = baseTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }
}