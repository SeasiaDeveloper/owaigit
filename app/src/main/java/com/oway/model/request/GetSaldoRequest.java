package com.oway.model.request;

public class GetSaldoRequest {
    private String ekl_customer;
    private String access_token;

    public String getEkl_customer() {
        return ekl_customer;
    }

    public void setEkl_customer(String ekl_customer) {
        this.ekl_customer = ekl_customer;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
