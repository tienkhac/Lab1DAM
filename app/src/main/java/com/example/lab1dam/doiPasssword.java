package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.MODEL.NguoiDung;

public class doiPasssword extends AppCompatActivity {
    EditText tenTK,edPass,edRePass;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đổi Mật Khẩu");
        setContentView(R.layout.activity_doi_passsword);
        tenTK = findViewById(R.id.tenTK);
        edPass = findViewById(R.id.edPass);
        edRePass = findViewById(R.id.edRePass);
    }
    public int validateForm(){
        int check = 1;
        if(edPass.getText().length() == 0 || edRePass.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            check = -1;
        }else {
            String pass = edPass.getText().toString();
            String repass = edRePass.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(getApplicationContext(),"Mật Khẩu Không trùng khớp", Toast.LENGTH_LONG).show();
                check= -1;
            }
        }
        return check;
    }
    public void changePassword(View view){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String strUserName = pref.getString("USERNAME","");
        nguoiDungDAO = new NguoiDungDAO(doiPasssword.this);
        NguoiDung user = new NguoiDung(strUserName,edPass.getText().toString(),"","");
        try{
            if(validateForm()>0){
                if ((nguoiDungDAO.
                        changePasswordNguoiDung(user)>0)){
                    Toast.makeText(getApplicationContext(),"Lưu Thành Công", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Lưu Thất Bại", Toast.LENGTH_LONG).show();
                }
            }
            finish();
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }
}
