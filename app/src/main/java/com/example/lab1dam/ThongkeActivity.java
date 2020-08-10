package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.lab1dam.DAO.HoaDonChiTietDAO;

public class ThongkeActivity extends AppCompatActivity {
TextView tvThongKeNgay,tvThongKeThang, tvThongKeNam;
HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thống Kê");
        setContentView(R.layout.activity_thongke);
        tvThongKeNam = findViewById(R.id.tvThongKeNam);
        tvThongKeNgay = findViewById(R.id.tvThongKeNgay);
        tvThongKeThang = findViewById(R.id.tvThongKeThang);

        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvThongKeThang.setText("Tháng này: "+hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvThongKeNgay.setText("Hôm nay: "+hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThongKeNam.setText("Năm nay: "+hoaDonChiTietDAO.getDoanhThuTheoNam());
    }
}
