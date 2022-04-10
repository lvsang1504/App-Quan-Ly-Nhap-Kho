package com.nhom2.qly_nhap_kho.dao;

public class TableDataBaoCao2 {
    private String maKho;
    private String tenKho;

    public TableDataBaoCao2(String maKho, String tenKho) {
        this.maKho=maKho;
        this.tenKho=tenKho;
    }

    public String getMaKho() {
        return maKho;
    }

    public void setMaKho(String maKho) {
        this.maKho = maKho;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }
}
