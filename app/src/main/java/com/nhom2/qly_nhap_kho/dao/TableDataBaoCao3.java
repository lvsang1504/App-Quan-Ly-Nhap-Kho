package com.nhom2.qly_nhap_kho.dao;

public class TableDataBaoCao3 {
    private int soPhieu;
    private String ngayLap;
    private String tenKho;

    public TableDataBaoCao3(int soPhieu, String ngayLap, String tenKho) {
        this.soPhieu = soPhieu;
        this.ngayLap = ngayLap;
        this.tenKho = tenKho;
    }

    public int getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(int soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }
}
