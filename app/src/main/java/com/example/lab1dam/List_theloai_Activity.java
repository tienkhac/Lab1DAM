package com.example.lab1dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lab1dam.Adapter.NguoidungAdapter;
import com.example.lab1dam.Adapter.TheloaiAdapter;
import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.DAO.TheLoaiDAO;
import com.example.lab1dam.MODEL.NguoiDung;
import com.example.lab1dam.MODEL.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class List_theloai_Activity extends AppCompatActivity {
    public  static List<TheLoai> dsTheLoai = new ArrayList<>();
    ListView lvtheloai;
    TheloaiAdapter adapter = null;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thể Loại");
        setContentView(R.layout.activity_list_theloai);

        lvtheloai = findViewById(R.id.lvtheloai);
      registerForContextMenu(lvtheloai);
        theLoaiDAO = new TheLoaiDAO(List_theloai_Activity.this);
        dsTheLoai = theLoaiDAO.getAllTheLoai();

        adapter = new TheloaiAdapter(this,dsTheLoai);
        lvtheloai.setAdapter(adapter);

        lvtheloai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(List_theloai_Activity.this,TheLoaiActivity.class);
                Bundle b = new Bundle();
                b.putString("MATHELOAI", dsTheLoai.get(i).getMaTheLoai());
                b.putString("TENTHELOAI", dsTheLoai.get(i).getTenTheLoai());
                b.putString("MOTA", dsTheLoai.get(i).getMoTa());
                b.putString("VITRI", String.valueOf(dsTheLoai.get(i).getViTri()));
                intent.putExtras(b);
                startActivity(intent);
        }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
               Intent intent = new Intent(List_theloai_Activity.this, TheLoaiActivity.class);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter.changeDataset(dsTheLoai);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        menu.setHeaderTitle("Chọn Thông Tin");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ctx_edit:
                Intent intent1 = new Intent(List_theloai_Activity.this, TheLoaiActivity.class);
                startActivity(intent1);
                return (true);
            case R.id.menu_ctx_del:
                Intent intent2 = new Intent(List_theloai_Activity.this, TheLoaiActivity.class);
                startActivity(intent2);
                return (true);
        }
        return super.onContextItemSelected(item);
    }}