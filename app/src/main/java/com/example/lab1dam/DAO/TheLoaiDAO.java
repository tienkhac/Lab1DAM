package com.example.lab1dam.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lab1dam.Database.DatabaseHelper;
import com.example.lab1dam.MODEL.HoaDon;
import com.example.lab1dam.MODEL.NguoiDung;
import com.example.lab1dam.MODEL.Sach;
import com.example.lab1dam.MODEL.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public static final String TABLE_NAME="TheLoai";
    public static final String SQL_THE_LOAI="CREATE TABLE TheLoai (matheloai text primary key, " +
            "tentheloai text, mota text, vitri int);";
    public static final String TAG = "TheLoaiDAO";
    public TheLoaiDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert du lieu
    public int inserttheLoai(TheLoai theloai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theloai.getMaTheLoai());
        values.put("tentheloai",theloai.getTenTheLoai());
        values.put("mota",theloai.getMoTa());
        values.put("vitri",theloai.getViTri());
        if (checkPrimaryKey(theloai.getMaTheLoai())) {
            int result = db.update(TABLE_NAME, values, "matheloai=?", new String[]{theloai.getMaTheLoai()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_NAME, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAllEmployee
    public List<TheLoai> getAllTheLoai() {
        List<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            TheLoai ee = new TheLoai();
            ee.setMaTheLoai(c.getString(0));
            ee.setTenTheLoai(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setViTri(c.getInt(3));
            dsTheLoai.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }

     //update
     public int updateTheLoai(TheLoai theloai){
         ContentValues values = new ContentValues();
         values.put("matheloai",theloai.getMaTheLoai());
         values.put("tentheloai",theloai.getTenTheLoai());
         values.put("mota",theloai.getMoTa());
         values.put("vitri",theloai.getViTri());
         int result = db.update(TABLE_NAME, values,"matheloai=?",new String[]{theloai.getMaTheLoai()});
         if (result==0){
             return -1;
         }
         return 1;
     }

     //delete
     public int deleteTheLoaiByID(String matheloai){
         int result = db.delete(TABLE_NAME,"matheloai=?",new String[]{matheloai});
         if (result == 0)
             return -1;
         return 1;
     }

     //check
    private boolean checkPrimaryKey(String strPrimaryKey) {
        //Select
        String[] columns = {"matheloai"};
        //Where
        String selection = "matheloai=?";
        //where clauser agruments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME,columns,selection,selectionArgs,null, null, null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i<=0){
                return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
