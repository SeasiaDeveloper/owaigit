package com.oway.model.request;

/**
 * Created by KaurAmanpreet on 29-Jun-18.
 */

public class LoginRequest {
    private String cardnum;

    private String password;

    private String devicetype;

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    private String version;

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest [cardnum = " + cardnum + ", password = " + password + "]";
    }
}
