package com.oway.model.response;

public class CancelRideReasonResponse {


    /**
     * errNumber : 899
     * respTime : 2019-09-30 06:34:41
     * status : FAILED
     * respMessage : THIS API NOT USED BECAUSE FLOW CHANGED
     */

    private String errNumber;
    private String respTime;
    private String status;
    private String respMessage;

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