package com.example.lab1dam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lab1dam.DAO.HoaDonDAO;
import com.example.lab1dam.MODEL.HoaDon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HoadonActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
EditText edmahoadon,edngaymua;
HoaDonDAO hoaDonDAO;
SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
private int mYear, mMonth, mDay;
Button picDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Thêm Hóa Đơn");
        setContentView(R.layout.activity_hoadon);
        edmahoadon = findViewById(R.id.edmahoadon);
        edngaymua = findViewById(R.id.edngaymua);
        picDate = findViewById(R.id.picDate);
    }
     @Override
     public void onDateSet(DatePicker view, int year, int month, int day){
         Calendar cal = new GregorianCalendar(year,month,day);
         setDate(cal);
     }

    private void setDate(final Calendar cal) {
        edngaymua.setText(sdf.format(cal.getTime()));
    }


    public  void datePicker(View view) {
        if (view == picDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            edngaymua.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
    public void addHoadon(View view){
        hoaDonDAO = new HoaDonDAO(HoadonActivity.this);

        try{
            if (validation() < 0){
                    Toast.makeText(getApplicationContext(),"Vui Lòng Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }else {
                  HoaDon hoaDon = new HoaDon(edmahoadon.getText().toString(),sdf.parse(edngaymua.getText().toString()));
                  if (hoaDonDAO.insertHoaDon(hoaDon) > 0){
                      Toast.makeText(getApplicationContext(),"Thêm Thành Công", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(HoadonActivity.this, Hoa_don_chi_tiet.class);
                      Bundle b = new Bundle();
                      b.putString("MAHOADON", edmahoadon.getText().toString());
                      intent.putExtras(b);
                      startActivity(intent);
                }else {
                      Toast.makeText(getApplicationContext(),"Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                  }
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }
    private int validation() {
        if (edmahoadon.getText().toString().isEmpty() || edngaymua.getText().toString().isEmpty()){
            return -1;
        }
        return 1;
    }
}
