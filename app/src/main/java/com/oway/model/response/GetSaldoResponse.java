package com.oway.model.response;

import java.util.List;

public class GetSaldoResponse
{


    /**
     * errNumber : 0
     * userID : EKL0098679
     * carier : FREE
     * h2hcarier : -
     * merchant_name : -
     * Balance : [{"id_member":"EKL0098679","sisa_uang":"1204000.00","carier_member":"FREE","bonus_member":"0.00","saldo_driver":"1000000.00","saldo_merchant":"0","saldo_driver_operasional":"0"}]
     * respTime : 2019-10-04 14:04:41
     * status : SUCCESS
     * respMessage : SUCCESS
     * image_slider : [{"id":1,"url":"https://development.owai.co.id/uploads/slider_home_1.jpg","description":""},{"id":2,"url":"https://development.owai.co.id/uploads/slider_home_2.jpg","description":""},{"id":3,"url":"https://development.owai.co.id/uploads/slider_home_3.jpg","description":""}]
     * Code : 200
     */

    private String errNumber;
    private String userID;
    private String carier;
    private String h2hcarier;
    private String merchant_name;
    private String respTime;
    private String status;
    private String respMessage;
    private int Code;
    private List<BalanceBean> Balance;
    private List<ImageSliderBean> image_slider;

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

    public String getMerchant_name() {
        return merchant_name;
    }

    public void setMerchant_name(String merchant_name) {
        this.merchant_name = merchant_name;
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

    public List<BalanceBean> getBalance() {
        return Balance;
    }

    public void setBalance(List<BalanceBean> Balance) {
        this.Balance = Balance;
    }

    public List<ImageSliderBean> getImage_slider() {
        return image_slider;
    }

    public void setImage_slider(List<ImageSliderBean> image_slider) {
        this.image_slider = image_slider;
    }

    public static class BalanceBean {
        /**
         * id_member : EKL0098679
         * sisa_uang : 1204000.00
         * carier_member : FREE
         * bonus_member : 0.00
         * saldo_driver : 1000000.00
         * saldo_merchant : 0
         * saldo_driver_operasional : 0
         */

        private String id_member;
        private String sisa_uang;
        private String carier_member;
        private String bonus_member;
        private String saldo_driver;
        private String saldo_merchant;
        private String saldo_driver_operasional;

        public String getId_member() {
            return id_member;
        }

        public void setId_member(String id_member) {
            this.id_member = id_member;
        }

        public String getSisa_uang() {
            return sisa_uang;
        }

        public void setSisa_uang(String sisa_uang) {
            this.sisa_uang = sisa_uang;
        }

        public String getCarier_member() {
            return carier_member;
        }

        public void setCarier_member(String carier_member) {
            this.carier_member = carier_member;
        }

        public String getBonus_member() {
            return bonus_member;
        }

        public void setBonus_member(String bonus_member) {
            this.bonus_member = bonus_member;
        }

        public String getSaldo_driver() {
            return saldo_driver;
        }

        public void setSaldo_driver(String saldo_driver) {
            this.saldo_driver = saldo_driver;
        }

        public String getSaldo_merchant() {
            return saldo_merchant;
        }

        public void setSaldo_merchant(String saldo_merchant) {
            this.saldo_merchant = saldo_merchant;
        }

        public String getSaldo_driver_operasional() {
            return saldo_driver_operasional;
        }

        public void setSaldo_driver_operasional(String saldo_driver_operasional) {
            this.saldo_driver_operasional = saldo_driver_operasional;
        }
    }

    public static class ImageSliderBean {
        /**
         * id : 1
         * url : https://development.owai.co.id/uploads/slider_home_1.jpg
         * description :
         */

        private int id;
        private String url;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}