package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String strUser,strPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ứng Dụng Quản Lý Sách");
        setContentView(R.layout.activity_main);
        if (checkLoginShap()<0){
            Intent i = new Intent(MainActivity.this, LoginActivity.class );
            startActivity(i);
        }
    }

    private int checkLoginShap() {
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        boolean chk = pref.getBoolean("REMEMBER", false);
        if (chk){
            strUser = pref.getString("USERNAME","");
            strPass = pref.getString("PASSWORD","");
            return 1;
        }
        return -1;
    }

    public void viewNguoiDung(View view){
        Intent intent = new Intent(getApplicationContext(), List_nguoidung.class);
        startActivity(intent);
    }
    public void viewTheLoai(View view){
        Intent intent = new Intent(getApplicationContext(), List_theloai_Activity.class);
        startActivity(intent);
    }
    public void viewBook(View view){
        Intent intent = new Intent(getApplicationContext(), List_book.class);
        startActivity(intent);
    }
    public void viewHoaDon(View view){
        Intent intent = new Intent(getApplicationContext(), list_hoa_don.class);
        startActivity(intent);
    }
    public void viewBanChay(View view){
        Intent intent = new Intent(getApplicationContext(), Luot_sach_ban_chay.class);
        startActivity(intent);
    }

    public void viewThongKe(View view){
        Intent intent = new Intent(getApplicationContext(), ThongkeActivity.class);
        startActivity(intent);
    }
    }

