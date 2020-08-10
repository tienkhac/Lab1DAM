package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab1dam.Adapter.ThongkeAdapter;
import com.example.lab1dam.DAO.HoaDonChiTietDAO;
import com.example.lab1dam.DAO.HoaDonDAO;
import com.example.lab1dam.DAO.SachDAO;
import com.example.lab1dam.MODEL.HoaDon;
import com.example.lab1dam.MODEL.HoaDonChiTiet;
import com.example.lab1dam.MODEL.Sach;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hoa_don_chi_tiet extends AppCompatActivity {
EditText edMahoadon,edMasach,edSoluongmua;
TextView tvThanhtien;
HoaDonChiTietDAO hoaDonChiTietDAO;
SachDAO sachDAO;
public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
ListView lvThongke;
ThongkeAdapter adapter = null;
double thanhtien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Chi tiết hóa đơn");
        setContentView(R.layout.activity_hoa_don_chi_tiet);
         edMahoadon = findViewById( R.id.edMahoadon);
         edSoluongmua = findViewById(R.id.edSoluongmua);
         edMasach = findViewById(R.id.edMasach);
         lvThongke = findViewById(R.id.lvThongke);
         tvThanhtien = findViewById(R.id.tvThanhtien);

         adapter = new ThongkeAdapter(this,dsHDCT);
         lvThongke.setAdapter(adapter);

        Intent in =  getIntent();
        Bundle  b = in.getExtras();

        if (b != null){
            edMahoadon.setText(b.getString("MAHOADON"));
        }
    }
    public void AddHoadonchitiet(View view){
        hoaDonChiTietDAO = new HoaDonChiTietDAO(Hoa_don_chi_tiet.this);
        sachDAO = new SachDAO(Hoa_don_chi_tiet.this);
        try{
            if (validation()<0){
                Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_LONG).show();
            }else {
                Sach sach = SachDAO.getSachByID(edMasach.getText().toString());
                if (sach!=null){
                   int pos = checkMaSach(dsHDCT,edMasach.getText().toString());
                   HoaDon hoaDon = new HoaDon(edMahoadon.getText().toString(),new Date());
                   HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet(1,hoaDon,sach,Integer.parseInt(edSoluongmua.getText().toString()));
                   if (pos>=0) {
                       int soluong = dsHDCT.get(pos).getSoLuongMua();
                       hoaDonChiTiet.setSoLuongMua(soluong + Integer.parseInt(edSoluongmua.getText().toString()));
                       dsHDCT.set(pos,hoaDonChiTiet);
                   }else {
                       dsHDCT.add(hoaDonChiTiet);
                   }
                   adapter.changeDataset(dsHDCT);
                }else {
                    Toast.makeText(getApplicationContext(),"Mã Sách Không Tồn Tại", Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }

    public void Thanhtoanhoadon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(Hoa_don_chi_tiet.this);
        //Tinh tien
        thanhtien = 0;
        try{
            for (HoaDonChiTiet hd: dsHDCT){
                hoaDonChiTietDAO.insertHoaDonChiTiet(hd);
                thanhtien = thanhtien + hd.getSoLuongMua() * hd.getSach().getGiaBia();
        }
            tvThanhtien.setText("Tổng tiền: " + thanhtien);
    }catch (Exception ex) {
            Log.e("Error",ex.toString());
        }
    }
    private int checkMaSach(List<HoaDonChiTiet> lsHD, String maSach) {
    int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
        HoaDonChiTiet hd = lsHD.get(i);
        if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)) {
        pos = i;
        break;
        }
        }
        return pos;
        }
private int validation() {
        if (edMasach.getText().toString().isEmpty() || edSoluongmua.getText().toString().isEmpty() || edMahoadon.getText().toString().isEmpty()){
        return -1;
        }
        return 1;
        }
        }


