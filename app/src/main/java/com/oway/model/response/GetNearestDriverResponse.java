package com.oway.model.response;


/**
 * Created by KaurAmanpreet on 29-Jun-18.
 */

public class GetNearestDriverResponse {


    /**
     * errNumber : 0
     * userID : EKL0098657
     * mbr_token : 042b989d3ee1beb88d775c48cf67e29c
     * carier : FREE
     * h2hcarier : -
     * respTime : 2019-09-18 15:02:37
     * status : SUCCESS
     * respMessage : SUCCESS
     *
     * Code : 200
     */

    private String errNumber;
    private String userID;
    private String mbr_token;
    private String carier;
    private String h2hcarier;
    private String respTime;
    private String status;
    private String respMessage;
    private int Code;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private String foto;
    private String nama;

    public String getErrNumber() {
        return errNumber;
    }

    public void setErrNumber(String errNumber) {
        this.errNumber = errNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMbr_token() {
        return mbr_token;
    }

    public void setMbr_token(String mbr_token) {
        this.mbr_token = mbr_token;
    }

    public String getCarier() {
        return carier;
    }

    public void setCarier(String carier) {
        this.carier = carier;
    }

    public String getH2hcarier() {
        return h2hcarier;
    }

    public void setH2hcarier(String h2hcarier) {
        this.h2hcarier = h2hcarier;
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

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }
}