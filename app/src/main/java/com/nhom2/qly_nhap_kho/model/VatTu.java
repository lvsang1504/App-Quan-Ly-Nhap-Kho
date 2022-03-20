package com.nhom2.qly_nhap_kho.model;

public class VatTu {
    public String MaVT;
    public String TenVt;
    public String XuatXu;

    public VatTu(String maVT, String tenVt, String xuatXu) {
        MaVT = maVT;
        TenVt = tenVt;
        XuatXu = xuatXu;
    }



    public void setMaVT(String maVT) {
        MaVT = maVT;
    }

    public void setTenVt(String tenVt) {
        TenVt = tenVt;
    }

    public void setXuatXu(String xuatXu) {
        XuatXu = xuatXu;
    }

    public String getMaVT() {
        return MaVT;
    }

    public String getTenVt() {
        return TenVt;
    }

    public String getXuatXu() {
        return XuatXu;
    }
}
