package com.example.lab1dam.MODEL;

import androidx.annotation.NonNull;

import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private Date ngayMua;

    public HoaDon() {
    }

    public HoaDon(String maHoaDon, Date ngayMua) {
        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    @NonNull
    @Override
    public String toString() {
        return "Hoadon{" +
                "Mahoadon'" + maHoaDon + '\'' +
              ", Ngaymua=" + ngayMua +
              '}';
    }
}
