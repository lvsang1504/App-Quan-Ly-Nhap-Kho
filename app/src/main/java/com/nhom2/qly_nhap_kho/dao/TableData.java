package com.nhom2.qly_nhap_kho.dao;

public class TableData {
    public String maVT;
    public String tenVT;
    public String xuatXu;
    public String DVT;
    public int soLuong;

    public TableData(String maVT, String tenVT, String xuatXu, String DVT, int soLuong) {
        this.maVT = maVT;
        this.tenVT = tenVT;
        this.xuatXu = xuatXu;
        this.DVT = DVT;
        this.soLuong = soLuong;
    }
}
