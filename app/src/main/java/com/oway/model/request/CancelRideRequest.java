package com.oway.model.request;



public class CancelRideRequest {


    /**
     * id_transaksi : {{idTransaksi}}
     * access_token : {{accessToken_customer}}
     */

    private String id_transaksi;
    private String access_token;

    public String getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(String id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
