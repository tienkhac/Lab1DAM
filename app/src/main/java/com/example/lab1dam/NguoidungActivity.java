package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.MODEL.NguoiDung;

public class NguoidungActivity extends AppCompatActivity {
    EditText edTenDangNhap, edMatKhau, edNhacLaiMatKhau,edHoVaTen,edPhone;
    Button btnThem, btnHuy, btnXemDanhSach;
    NguoiDungDAO nguoiDungDAO;
    private int validateFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thêm Người Dùng");
        setContentView(R.layout.activity_nguoidung);
        edTenDangNhap=findViewById(R.id.UserName);
        edMatKhau=findViewById(R.id.PassWord);
        edNhacLaiMatKhau=findViewById(R.id.RePass);
        edHoVaTen=findViewById(R.id.hovaten);
        edPhone = findViewById(R.id.edPhone);
        btnThem=findViewById(R.id.btnthemUser);
        btnHuy=findViewById(R.id.btnHuyUser);
        btnXemDanhSach=findViewById(R.id.btnDanhSach);
    }
    public void addUser(View view){
        nguoiDungDAO = new NguoiDungDAO(NguoidungActivity.this);
        NguoiDung user = new NguoiDung(edTenDangNhap.getText().toString(),
                edMatKhau.getText().toString(),
                edPhone.getText().toString(),
                edHoVaTen.getText().toString());
        try{
            if (validateFrom()>0){
                if(nguoiDungDAO.insertNguoiDung(user) > 0){
                    Toast.makeText(getApplicationContext(), "Thêm Thành Công",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Thêm Thất Bại",Toast.LENGTH_LONG).show();
                }
            }
        }catch (Exception ex){
            Log.d("Error",ex.toString());
        }
    }

    private int validateFrom() {
        int check = 1;
        if (edNhacLaiMatKhau.getText().length() == 0 ||
                edMatKhau.getText().length() == 0 ||
                edHoVaTen.getText().length() == 0 ||
                edTenDangNhap.getText().length() == 0 ){
            Toast.makeText(getApplicationContext(), "Bạn Phải Nhập Đầy Đủ Thông Tin",Toast.LENGTH_LONG).show();
            check = -1;
        }else {
            String pass = edMatKhau.getText().toString();
            String rePass = edNhacLaiMatKhau.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getApplicationContext(), "Mật Khẩu Không Trùng Khớp",Toast.LENGTH_LONG).show();
                check = -1;
            }
        }
        return check;
    }

    public void showUser(View view){
        finish();
    }
    public void HuyNguoiDung(View view){
        finish();
    }
}
