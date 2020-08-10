package com.example.lab1dam;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1dam.DAO.NguoiDungDAO;

public class LoginActivity extends AppCompatActivity {
    EditText edUserName,edPassword;
    Button btn_Dangnhap, btn_exit;
    String strUser,strPass;
    CheckBox chkLuu;
    NguoiDungDAO nguoiDungDAO;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đăng Nhập Hệ Thống");
        setContentView(R.layout.activity_login);
        btn_Dangnhap = findViewById(R.id.btn_dangnhap);
        btn_exit = findViewById(R.id.btn_exit);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        chkLuu = findViewById(R.id.chkLuu);
        nguoiDungDAO = new NguoiDungDAO(LoginActivity.this);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn Có Muốn Đóng Ứng Dụng Không ?");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });
    }
    public void checkLogin(View view){
        strPass = edPassword.getText().toString();
        strUser = edUserName.getText().toString();
        if(strUser.isEmpty() || strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không được bỏ trống",Toast.LENGTH_LONG).show();
        }else {
            if(nguoiDungDAO.checkLogin(strPass,strUser)>0){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_LONG).show();
                finish();
            }
            if (strPass.equalsIgnoreCase("admin") && strUser.equalsIgnoreCase("admin")){
                chkLuu(strUser,strPass,chkLuu.isChecked());
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập và mật khẩu không đúng",Toast.LENGTH_LONG).show();
            }
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void chkLuu(String strUser, String strPass, boolean checked) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!checked){
            //Xóa tình trạng lưu trước đó
            edit.clear();
        }else {
            //Lư dữ liệu
            edit.putString("USERNAME",strUser);
            edit.putString("PASSWORD", strPass);
            edit.putBoolean("REMEMBER",checked);
        }
        //Lưu lại toàn bộ
        edit.commit();
    }

}

