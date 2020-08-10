package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;


import com.example.lab1dam.Adapter.ThongkeAdapter;
import com.example.lab1dam.DAO.HoaDonChiTietDAO;
import com.example.lab1dam.MODEL.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class List_hoa_don_chi_tiet_by_id extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    ThongkeAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hóa Đơn Chi Tiết");
        setContentView(R.layout.activity_list_hoa_don_chi_tiet_by_id);
        lvCart = findViewById(R.id.lvHoadonchitiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(List_hoa_don_chi_tiet_by_id.this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new ThongkeAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}
