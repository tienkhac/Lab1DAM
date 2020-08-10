package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lab1dam.DAO.NguoiDungDAO;

import java.security.KeyStore;

public class Nguoi_dung_detal extends AppCompatActivity {
EditText edPhone,edFullName;
NguoiDungDAO nguoiDungDAO;
String username, fullname, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Chi Tiết Người Dùng");
        setContentView(R.layout.activity_nguoi_dung_detal);
        edFullName = findViewById(R.id.edFullName);
        edPhone = findViewById(R.id.edPhone);
        nguoiDungDAO = new NguoiDungDAO(this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        fullname= b.getString("FULLNAME");
        phone = b.getString("PHONE");
        username = b.getString("USERNAME");
        edFullName.setText(fullname);
        edPhone.setText(phone);
    }
    public void UpdateUser(View view){
        if (nguoiDungDAO.updateInfoNguoiDung(username,edPhone.getText().toString(), edFullName.getText().toString()) > 0){
            Toast.makeText(getApplicationContext(),"Lưu thành công", Toast.LENGTH_SHORT).show();
        }
    }
    public void Huy(View view){
        finish();
    }
}
