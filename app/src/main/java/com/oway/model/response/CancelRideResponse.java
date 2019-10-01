package com.oway.model.response;

public class CancelRideResponse {


    /**
     * Code : 400
     * errNumber : 801
     * respTime : 2019-09-30 06:00:04
     * status : FAILED
     * respMessage : File : /var/www/development.owai.co.id/laravel/app/Http/Controllers/RideController.php
     Line : 261
     Code : 0
     Message : Undefined variable: reason
     */

    private int Code;
    private String errNumber;
    private String respTime;
    private String status;
    private String respMessage;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }
}