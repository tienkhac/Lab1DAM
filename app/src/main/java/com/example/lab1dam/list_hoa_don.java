package com.example.lab1dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.lab1dam.Adapter.HoadonAdapter;
import com.example.lab1dam.Adapter.SachAdapter;
import com.example.lab1dam.DAO.HoaDonDAO;
import com.example.lab1dam.DAO.SachDAO;
import com.example.lab1dam.MODEL.HoaDon;
import com.example.lab1dam.MODEL.Sach;

import java.util.ArrayList;
import java.util.List;

public class list_hoa_don extends AppCompatActivity {
    public  static List<HoaDon> dsHoaDon = new ArrayList<>();
    ListView lvHoaDon;
    HoadonAdapter adapter = null;
    HoaDonDAO hoaDonDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Hóa Đơn");
        setContentView(R.layout.activity_list_hoa_don);
        lvHoaDon = findViewById(R.id.lvHoadon);
        hoaDonDAO = new HoaDonDAO(list_hoa_don.this);

        try{
            dsHoaDon = hoaDonDAO.getAllHoaDon();
        }catch (Exception ex){
            Log.d("Error: ",ex.toString());
        }
        adapter = new HoadonAdapter(this,dsHoaDon);
        lvHoaDon.setAdapter(adapter);
        lvHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HoaDon hoaDon = (HoaDon) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(list_hoa_don.this,List_hoa_don_chi_tiet_by_id.class);
                Bundle b = new Bundle();
                b.putString("MAHOADON", hoaDon.getMaHoaDon());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        //textFilterz
        lvHoaDon.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edSearch);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                System.out.println("Text[" + s +  "] - Star [" + i + "] - Before [" + i1 + "] - " +
                        "Count [" + i2 + "]");
                if ( i2 < i1 ){
                    adapter.resetData();
                }
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
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
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(list_hoa_don.this,HoadonActivity.class);
                startActivity(intent);
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
    }

