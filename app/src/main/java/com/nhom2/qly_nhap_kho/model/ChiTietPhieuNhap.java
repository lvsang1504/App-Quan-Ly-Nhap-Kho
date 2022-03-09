package com.nhom2.qly_nhap_kho.model;

public class ChiTietPhieuNhap {
    public int SoPhieu;
    public String MaVT;
    public String DVT;
    public int SoLuong;

    public ChiTietPhieuNhap(int soPhieu, String maVT, String DVT, int soLuong) {
        SoPhieu = soPhieu;
        MaVT = maVT;
        this.DVT = DVT;
        SoLuong = soLuong;
    }
}
