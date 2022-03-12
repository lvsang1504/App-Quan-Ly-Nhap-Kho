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
}
