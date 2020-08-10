package com.example.lab1dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.lab1dam.Adapter.NguoidungAdapter;
import com.example.lab1dam.Adapter.SachAdapter;
import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.DAO.SachDAO;
import com.example.lab1dam.MODEL.NguoiDung;
import com.example.lab1dam.MODEL.Sach;

import java.util.ArrayList;
import java.util.List;

public class List_nguoidung extends AppCompatActivity {
    public  static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    NguoidungAdapter adapter = null;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Người Dùng");
        setContentView(R.layout.activity_list_nguoidung);
        lvNguoiDung = findViewById(R.id.lvNguoiDung);


        nguoiDungDAO = new NguoiDungDAO(List_nguoidung.this);
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();

        adapter = new NguoidungAdapter(this,dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(List_nguoidung.this,Nguoi_dung_detal.class);
                Bundle b = new Bundle();
                b.putString("USERNAME", dsNguoiDung.get(i).getUserName());
                b.putString("PHONE", dsNguoiDung.get(i).getPhone());
                b.putString("FULLNAME", dsNguoiDung.get(i).getHoTen());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllNguoiDung();
        adapter.changeDataset(nguoiDungDAO.getAllNguoiDung());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.add:
                intent = new Intent(List_nguoidung.this,NguoidungActivity.class);
                startActivity(intent);
                return (true);

            case R.id.changePass:
                intent = new Intent(List_nguoidung.this,doiPasssword.class);
                startActivity(intent);
                return (true);

            case R.id.logOut:
                SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                //Xoa tinh trang luu truoc do
                edit.clear();
                edit.commit();
                intent = new Intent(List_nguoidung.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
