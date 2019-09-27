package com.oway.model.response;

public class GetEstimateBikeResponse {


    /**
     * Code : 200
     * errNumber : 0
     * respTime : 2019-09-26 01:34:07
     * price : {"cash":7000,"balance":7000}
     */

    private int Code;
    private String errNumber;
    private String respTime;
    private PriceBean price;

    public String getRespMessage() {
        return respMessage;
    }

    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

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

    public PriceBean getPrice() {
        return price;
    }

    public void setPrice(PriceBean price) {
        this.price = price;
    }

    public static class PriceBean {
        public long getCash() {
            return cash;
        }

        public void setCash(long cash) {
            this.cash = cash;
        }

        public long getBalance() {
            return balance;
        }

        public void setBalance(long balance) {
            this.balance = balance;
        }
        /**
         * cash : 7000
         * balance : 7000
         */
        private long cash;
        private long balance;

}}