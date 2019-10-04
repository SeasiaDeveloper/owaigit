package com.oway.model.response;

public class CancelRideResponse {


    /**
     * Code : 200
     * errNumber : 0
     * respTime : 2019-10-04 02:09:25
     * status : SUCCESS
     * id_transaksi : 236
     * respMessage : TRANSACTION SUCCESS CANCELLED
     */

    private int Code;
    private String errNumber;
    private String respTime;
    private String status;
    private int id_transaksi;
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

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }
}