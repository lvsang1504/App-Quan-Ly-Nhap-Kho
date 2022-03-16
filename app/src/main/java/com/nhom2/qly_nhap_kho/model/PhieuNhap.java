package com.nhom2.qly_nhap_kho.model;

import java.io.Serializable;

public class PhieuNhap implements Serializable {
    public int SoPhieu;
    public String NgayLap;
    public String MaKho;

    public PhieuNhap(int soPhieu, String ngayLap, String maKho) {
        SoPhieu = soPhieu;
        NgayLap = ngayLap;
        MaKho = maKho;
    }

    public void setSoPhieu(int soPhieu) {
        SoPhieu = soPhieu;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }

    public void setMaKho(String maKho) {
        MaKho = maKho;
    }

    public int getSoPhieu() {
        return SoPhieu;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public String getMaKho() {
        return MaKho;
    }
}
