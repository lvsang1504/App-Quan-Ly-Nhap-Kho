package com.nhom2.qly_nhap_kho.dao;

import java.util.ArrayList;
import java.util.List;

public class TableDataBaoCao4 {
    private String maVT;
    private String tenVT;
    private String DVT;
    private List<Integer> khoData = new ArrayList<>();

    public TableDataBaoCao4() {
    }

    public TableDataBaoCao4(String maVT, String tenVT, String DVT, List<Integer> danhSachKho) {
        this.maVT = maVT;
        this.tenVT = tenVT;
        this.DVT = DVT;
        this.khoData = danhSachKho;
    }

    public String getMaVT() {
        return maVT;
    }

    public void setMaVT(String maVT) {
        this.maVT = maVT;
    }

    public String getTenVT() {
        return tenVT;
    }

    public void setTenVT(String tenVT) {
        this.tenVT = tenVT;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public List<Integer> getDanhSachKho() {
        return khoData;
    }

    public void addKhoData(Integer kho) {
        this.khoData.add(kho);
    }

    public void setDanhSachKho(List<Integer> danhSachKho) {
        this.khoData = danhSachKho;
    }
}
