package com.oway.model.request;


public class RegisterRequest {


    /**
     * nama : Suko
     * email : syall@gmail.com
     * password : 1234567a8
     * pin : 111111a
     * uplineID : EKLXXXX
     * phone_number : 085859334412
     */

    private String nama;
    private String email;
    private String password;
    private String pin;
    private String uplineID;
    private String phone_number;
    private String address;
    private String city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private String province;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getUplineID() {
        return uplineID;
    }

    public void setUplineID(String uplineID) {
        this.uplineID = uplineID;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
