package com.oway.model.response;

import java.util.List;

public class GetPriceBySeatResponse {


    /**
     * Code : 200
     * errNumber : 0
     * respTime : 2019-10-03 01:48:00
     * status : SUCCESS
     * data : [{"seat":4,"cash":8000,"balance":8000},{"seat":6,"cash":12000,"balance":12000}]
     */

    private int Code;
    private String errNumber;
    private String respTime;


    private boolean isSelect;
    private String status;
    private List<DataBean> data;

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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * seat : 4
         * cash : 8000
         * balance : 8000
         */

        private int seat;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private String description;

        public double getCash() {
            return cash;
        }

        public void setCash(double cash) {
            this.cash = cash;
        }

        private double cash;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        private double balance;

        public int getSeat() {
            return seat;
        }

        public void setSeat(int seat) {
            this.seat = seat;
        }

    }
}