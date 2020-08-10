package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab1dam.Adapter.NguoidungAdapter;
import com.example.lab1dam.Adapter.SachAdapter;
import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.DAO.SachDAO;
import com.example.lab1dam.MODEL.NguoiDung;
import com.example.lab1dam.MODEL.Sach;

import java.util.ArrayList;
import java.util.List;

public class Luot_sach_ban_chay extends AppCompatActivity {
    public  static List<Sach> dsSach= new ArrayList<>();
    ListView lvBookTop;
    SachAdapter adapter = null;
    SachDAO sachDAO;
    EditText edThang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Top 10 Sách Bán Chạy");
        setContentView(R.layout.activity_luot_sach_ban_chay);
        lvBookTop = findViewById(R.id.lvBookTop);
        edThang = findViewById(R.id.edThang);
    }
    public void VIEW_SACH_TOP_10(View view){
        if(Integer.parseInt(edThang.getText().toString())>13 || Integer.parseInt(edThang.getText().toString())<0){
            Toast.makeText(getApplicationContext(),"Không Đúng Định Dang Tháng(1-12)",Toast.LENGTH_LONG).show();
        }else {
            sachDAO = new SachDAO(Luot_sach_ban_chay.this);
            dsSach = sachDAO.getSachTop10(edThang.getText().toString());

            adapter = new SachAdapter(this,dsSach);
            lvBookTop.setAdapter(adapter);
        }
    }
}
