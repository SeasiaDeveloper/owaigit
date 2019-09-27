package com.oway.model.response;

public class GetSaldoResponse
{
    private String respMessage;

    private String errNumber;

    private String respTime;

    private String merchant_name;

    private String h2hcarier;

    private String userID;

    private Balance[] Balance;

    private int Code;

    private String carier;

    private String status;

    public String getRespMessage ()
    {
        return respMessage;
    }

    public void setRespMessage (String respMessage)
    {
        this.respMessage = respMessage;
    }

    public String getErrNumber ()
    {
        return errNumber;
    }

    public void setErrNumber (String errNumber)
    {
        this.errNumber = errNumber;
    }

    public String getRespTime ()
    {
        return respTime;
    }

    public void setRespTime (String respTime)
    {
        this.respTime = respTime;
    }

    public String getMerchant_name ()
    {
        return merchant_name;
    }

    public void setMerchant_name (String merchant_name)
    {
        this.merchant_name = merchant_name;
    }

    public String getH2hcarier ()
    {
        return h2hcarier;
    }

    public void setH2hcarier (String h2hcarier)
    {
        this.h2hcarier = h2hcarier;
    }

    public String getUserID ()
    {
        return userID;
    }

    public void setUserID (String userID)
    {
        this.userID = userID;
    }

    public Balance[] getBalance ()
    {
        return Balance;
    }

    public void setBalance (Balance[] Balance)
    {
        this.Balance = Balance;
    }

    public int getCode ()
    {
        return Code;
    }

    public void setCode (int Code)
    {
        this.Code = Code;
    }

    public String getCarier ()
    {
        return carier;
    }

    public void setCarier (String carier)
    {
        this.carier = carier;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [respMessage = "+respMessage+", errNumber = "+errNumber+", respTime = "+respTime+", merchant_name = "+merchant_name+", h2hcarier = "+h2hcarier+", userID = "+userID+", Balance = "+Balance+", Code = "+Code+", carier = "+carier+", status = "+status+"]";
    }

    public class Balance
    {
        private String bonus_member;

        private String saldo_merchant;

        private String saldo_driver;

        private String id_member;

        private String sisa_uang;

        private String carier_member;

        public String getBonus_member ()
        {
            return bonus_member;
        }

        public void setBonus_member (String bonus_member)
        {
            this.bonus_member = bonus_member;
        }

        public String getSaldo_merchant ()
        {
            return saldo_merchant;
        }

        public void setSaldo_merchant (String saldo_merchant)
        {
            this.saldo_merchant = saldo_merchant;
        }

        public String getSaldo_driver ()
        {
            return saldo_driver;
        }

        public void setSaldo_driver (String saldo_driver)
        {
            this.saldo_driver = saldo_driver;
        }

        public String getId_member ()
        {
            return id_member;
        }

        public void setId_member (String id_member)
        {
            this.id_member = id_member;
        }

        public String getSisa_uang ()
        {
            return sisa_uang;
        }

        public void setSisa_uang (String sisa_uang)
        {
            this.sisa_uang = sisa_uang;
        }

        public String getCarier_member ()
        {
            return carier_member;
        }

        public void setCarier_member (String carier_member)
        {
            this.carier_member = carier_member;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [bonus_member = "+bonus_member+", saldo_merchant = "+saldo_merchant+", saldo_driver = "+saldo_driver+", id_member = "+id_member+", sisa_uang = "+sisa_uang+", carier_member = "+carier_member+"]";
        }
    }


}