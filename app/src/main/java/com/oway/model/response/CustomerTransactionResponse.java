package com.oway.model.response;

public class CustomerTransactionResponse {


    /**
     * Code : 200
     * errNumber : 0
     * respTime : 2019-09-26 05:00:20
     * status : SUCCESS
     * transaksi : {"id":102,"latitude_start":"-7.5453971","longitude_start":"112.2417105","latitude_end":"7.0707256907261","longitude_end":"112.37143334001","jarak":1,"harga":2000,"waktu_order":"2019-09-26 17:00:20","waktu_selesai":null,"alamat_asal":"lamongan","alamat_tujuan":"turi","kode_promo":null,"kredit_promo":null,"biaya_akhir":null,"pakai_saldo":0,"fitur":1,"ekl_pelanggan":"EKL0098679","ekl_driver":null}
     */

    private int Code;
    private String errNumber;
    private String respTime;
    private String status;
    private TransaksiBean transaksi;

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

    public void setStatus(String status) {
        this.status = status;
    }

    public TransaksiBean getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(TransaksiBean transaksi) {
        this.transaksi = transaksi;
    }

    public static class TransaksiBean {
        /**
         * id : 102
         * latitude_start : -7.5453971
         * longitude_start : 112.2417105
         * latitude_end : 7.0707256907261
         * longitude_end : 112.37143334001
         * jarak : 1
         * harga : 2000
         * waktu_order : 2019-09-26 17:00:20
         * waktu_selesai : null
         * alamat_asal : lamongan
         * alamat_tujuan : turi
         * kode_promo : null
         * kredit_promo : null
         * biaya_akhir : null
         * pakai_saldo : 0
         * fitur : 1
         * ekl_pelanggan : EKL0098679
         * ekl_driver : null
         */

        private int id;
        private String latitude_start;
        private String longitude_start;
        private String latitude_end;
        private String longitude_end;
        private int jarak;
        private int harga;
        private String waktu_order;
        private Object waktu_selesai;
        private String alamat_asal;
        private String alamat_tujuan;
        private Object kode_promo;
        private Object kredit_promo;
        private Object biaya_akhir;
        private int pakai_saldo;
        private int fitur;
        private String ekl_pelanggan;
        private Object ekl_driver;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLatitude_start() {
            return latitude_start;
        }

        public void setLatitude_start(String latitude_start) {
            this.latitude_start = latitude_start;
        }

        public String getLongitude_start() {
            return longitude_start;
        }

        public void setLongitude_start(String longitude_start) {
            this.longitude_start = longitude_start;
        }

        public String getLatitude_end() {
            return latitude_end;
        }

        public void setLatitude_end(String latitude_end) {
            this.latitude_end = latitude_end;
        }

        public String getLongitude_end() {
            return longitude_end;
        }

        public void setLongitude_end(String longitude_end) {
            this.longitude_end = longitude_end;
        }

        public int getJarak() {
            return jarak;
        }

        public void setJarak(int jarak) {
            this.jarak = jarak;
        }

        public int getHarga() {
            return harga;
        }

        public void setHarga(int harga) {
            this.harga = harga;
        }

        public String getWaktu_order() {
            return waktu_order;
        }

        public void setWaktu_order(String waktu_order) {
            this.waktu_order = waktu_order;
        }

        public Object getWaktu_selesai() {
            return waktu_selesai;
        }

        public void setWaktu_selesai(Object waktu_selesai) {
            this.waktu_selesai = waktu_selesai;
        }

        public String getAlamat_asal() {
            return alamat_asal;
        }

        public void setAlamat_asal(String alamat_asal) {
            this.alamat_asal = alamat_asal;
        }

        public String getAlamat_tujuan() {
            return alamat_tujuan;
        }

        public void setAlamat_tujuan(String alamat_tujuan) {
            this.alamat_tujuan = alamat_tujuan;
        }

        public Object getKode_promo() {
            return kode_promo;
        }

        public void setKode_promo(Object kode_promo) {
            this.kode_promo = kode_promo;
        }

        public Object getKredit_promo() {
            return kredit_promo;
        }

        public void setKredit_promo(Object kredit_promo) {
            this.kredit_promo = kredit_promo;
        }

        public Object getBiaya_akhir() {
            return biaya_akhir;
        }

        public void setBiaya_akhir(Object biaya_akhir) {
            this.biaya_akhir = biaya_akhir;
        }

        public int getPakai_saldo() {
            return pakai_saldo;
        }

        public void setPakai_saldo(int pakai_saldo) {
            this.pakai_saldo = pakai_saldo;
        }

        public int getFitur() {
            return fitur;
        }

        public void setFitur(int fitur) {
            this.fitur = fitur;
        }

        public String getEkl_pelanggan() {
            return ekl_pelanggan;
        }

        public void setEkl_pelanggan(String ekl_pelanggan) {
            this.ekl_pelanggan = ekl_pelanggan;
        }

        public Object getEkl_driver() {
            return ekl_driver;
        }

        public void setEkl_driver(Object ekl_driver) {
            this.ekl_driver = ekl_driver;
        }
    }
}