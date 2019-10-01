package com.oway.model.request;



public class CancelRideReasonRequest {


    /**
     * ekl_customer : {{userID_customer}}
     * id_transaksi : {{idTransaksi}}
     * reason : I am not ready togo
     * access_token : {{accessToken_customer}}
     */

    private String ekl_customer;
    private String id_transaksi;
    private String reason;
    private String access_token;

    public String getEkl_customer() {
        return ekl_customer;
    }

    public void setEkl_customer(String ekl_customer) {
        this.ekl_customer = ekl_customer;
    }

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
