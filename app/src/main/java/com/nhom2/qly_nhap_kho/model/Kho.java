package com.nhom2.qly_nhap_kho.model;

import java.io.Serializable;

public class Kho implements Serializable {
    private String MaKho,TenKho;

    public Kho(String maKho, String tenKho) {
        MaKho = maKho;
        TenKho = tenKho;
    }

    public void setMaKho(String maKho) {
        MaKho = maKho;
    }

    public void setTenKho(String tenKho) {
        TenKho = tenKho;
    }

    public String getMaKho() {
        return MaKho;
    }

    public String getTenKho() {
        return TenKho;
    }
}
