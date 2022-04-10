package com.nhom2.qly_nhap_kho.dao;

public class TableDataBaoCao1 {
    private String maKho;
    private String tenKho;
    private int soLoaiVatTu;

    public TableDataBaoCao1(String maKho, String tenKho, int soLoaiVatTu) {
        this.maKho = maKho;
        this.tenKho = tenKho;
        this.soLoaiVatTu = soLoaiVatTu;
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

    public int getSoLoaiVatTu() {
        return soLoaiVatTu;
    }

    public void setSoLoaiVatTu(int soLoaiVatTu) {
        this.soLoaiVatTu = soLoaiVatTu;
    }
}
