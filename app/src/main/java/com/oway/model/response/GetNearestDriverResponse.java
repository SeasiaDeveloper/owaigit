package com.oway.model.response;

import java.util.List;

public class GetNearestDriverResponse {


    /**
     * Code : 200
     * errNumber : 0
     * respTime : 2019-09-25 12:52:53
     * data : [{"ekl_driver":"EKL0098734","latitude":"-7.5452439","longitude":"112.7064384","updated_at":"2019-09-02 17:10:14","status":1,"distance":0},{"ekl_driver":"EKL0098700","latitude":"-7.5452439","longitude":"112.7064384","updated_at":"2019-09-04 17:22:26","status":1,"distance":0},{"ekl_driver":"EKL0098657","latitude":"-7.5452439","longitude":"112.7064384","updated_at":"2019-09-10 16:53:33","status":1,"distance":0}]
     */

    private int Code;
    private String errNumber;
    private String respTime;

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

    private String respMessage;
    private List<DataBean> data;

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getErrNumber() {
        return errNumber;
    }

    public void setErrNumber(String errNumber) {
        this.errNumber = errNumber;
    }

    public String getRespTime() {
        return respTime;
    }

    public void setRespTime(String respTime) {
        this.respTime = respTime;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * ekl_driver : EKL0098734
         * latitude : -7.5452439
         * longitude : 112.7064384
         * updated_at : 2019-09-02 17:10:14
         * status : 1
         * distance : 0
         */

        private String ekl_driver;
        private String latitude;
        private String longitude;
        private String updated_at;
        private int status;

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        private double distance;

        public String getEkl_driver() {
            return ekl_driver;
        }

        public void setEkl_driver(String ekl_driver) {
            this.ekl_driver = ekl_driver;
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

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }
}