package com.example.lab1dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab1dam.DAO.SachDAO;
import com.example.lab1dam.DAO.TheLoaiDAO;
import com.example.lab1dam.MODEL.Sach;
import com.example.lab1dam.MODEL.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class SachActivity extends AppCompatActivity {
SachDAO sachDAO;
TheLoaiDAO theLoaiDAO;
Spinner spnTheLoai;
EditText edMaSach,edTenSach,edTacGia,edNXB,edGiaBia,edSoLuong;
String maTheLoai = "";
List<TheLoai> listTheLoai =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thêm Sách");
        setContentView(R.layout.activity_sach);
        spnTheLoai = findViewById(R.id.spnTheLoai);
        getTheLoai();
        edGiaBia = findViewById(R.id.edGiaBia);
        edMaSach = findViewById(R.id.edMaSach);
        edTenSach = findViewById(R.id.edTenSach);
        edTacGia = findViewById(R.id.edTacGia);
        edNXB = findViewById(R.id.edNXB);
        edSoLuong = findViewById(R.id.edSoLuong);

        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maTheLoai = listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //loat data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if(b != null) {
        edMaSach.setText(b.getString("MASACH"));
        String maTheLoai = b.getString("MATHELOAI");
        edTenSach.setText(b.getString("TENSACH"));
        edNXB.setText(b.getString("NXB"));
        edTacGia.setText(b.getString("TACGIA"));
        edGiaBia.setText(b.getString("GIABIA"));
        edSoLuong.setText(b.getString("SOLUONG"));
        spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
    }}
   
    public void getTheLoai(){
        theLoaiDAO = new TheLoaiDAO(SachActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> adapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(adapter);
    }
public void addBook(View view){
    sachDAO = new SachDAO(SachActivity.this);
    Sach sach = new Sach(edMaSach.getText().toString(),
            maTheLoai,edTenSach.getText().toString(), edTacGia.getText().toString(),
            edNXB.getText().toString(), (int) Double.parseDouble(edGiaBia.getText().toString()),
            Integer.parseInt(edSoLuong.getText().toString()));
    try{
        if(sachDAO.insertSach(sach) > 0){
            Toast.makeText(getApplicationContext(), "Thêm Thành Công",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getApplicationContext(), "Thêm Thất Bại",Toast.LENGTH_LONG).show();
        }
    }
    catch (Exception ex){
        Log.d("Error",ex.toString());
    }
}
public void showBook(View view){
        finish();
}
public void outBook(View view){
        finish();
}


    private int checkPositionTheLoai(String maTheLoai) {
      for (int i = 0 ; i < listTheLoai.size(); i++){
          if(maTheLoai.equals(listTheLoai.get(i).getMaTheLoai())){
              return i;
          }
      }
      return 0;
    }
}
