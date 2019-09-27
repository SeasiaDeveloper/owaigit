package com.oway.model.response;

public class SendDriverResponse {


    /**
     * respTime : 2019-09-25 11:32:21
     * status : SUCCESS
     * respMessage : SUCCESS
     * Code : 200
     */

    private String respTime;
    private String status;
    private String respMessage;
    private int Code;

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

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }
}