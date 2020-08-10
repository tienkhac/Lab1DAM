package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.DAO.TheLoaiDAO;
import com.example.lab1dam.MODEL.TheLoai;

public class TheLoaiActivity extends AppCompatActivity {
    EditText edMaTheLoai, edTenTheLoai, edViTri, edMoTa;
    TheLoaiDAO theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thể Loại Sách");
        setContentView(R.layout.activity_the_loai);
        edMaTheLoai = findViewById(R.id.edmaTheLoai);
        edTenTheLoai = findViewById(R.id.edtenTheLoai);
        edViTri = findViewById(R.id.edvitri);
        edMoTa = findViewById(R.id.edmota);


        Intent in = getIntent();
        Bundle b = in.getExtras();

        if (b != null) {
            edMaTheLoai.setText(b.getString("MATHELOAI"));
            edTenTheLoai.setText(b.getString("TENTHELOAI"));
            edViTri.setText(b.getString("VITRI"));
            edMoTa.setText(b.getString("MOTA"));
        }
    }

    public void ThemTheLoai(View view){
        theLoaiDAO = new TheLoaiDAO(TheLoaiActivity.this);
        try{
            if (validateFrom()<0){
                Toast.makeText(getApplicationContext(), "Bạn Phải Nhập Đầy Đủ Thông Tin",Toast.LENGTH_LONG).show();
            }else {
                TheLoai theLoai = new TheLoai(edMaTheLoai.getText().toString(),
                        edTenTheLoai.getText().toString(), edMoTa.getText().toString(),
                        Integer.parseInt(edViTri.getText().toString()));
            if(theLoaiDAO.inserttheLoai(theLoai) > 0){
                    Toast.makeText(getApplicationContext(), "Thêm Thành Công",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Thêm Thât Bại",Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception ex){
            Log.d("Error",ex.toString());
        }
    }

    private int validateFrom() {
        int check = 1;
        if (edMaTheLoai.getText().length() == 0 ||
                edTenTheLoai.getText().length() == 0 ||
                edViTri.getText().length() == 0 ||
                edMoTa.getText().length() == 0 )
        {
            check = -1;
    }
        return check;
    }

    public void HuyTheLoai(View view){
        finish();
    }
    public void ShowTheLoai(View view){
      finish();
    }
}
