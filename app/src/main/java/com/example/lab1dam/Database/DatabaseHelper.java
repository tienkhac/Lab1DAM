package com.example.lab1dam.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.lab1dam.DAO.HoaDonChiTietDAO;
import com.example.lab1dam.DAO.HoaDonDAO;
import com.example.lab1dam.DAO.NguoiDungDAO;
import com.example.lab1dam.DAO.SachDAO;
import com.example.lab1dam.DAO.TheLoaiDAO;


public class   DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";
    public static final int VERSION = 1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NguoiDungDAO.SQL_NGUOI_DUNG);
        db.execSQL(TheLoaiDAO.SQL_THE_LOAI);
        db.execSQL(SachDAO.SQL_SACH);
        db.execSQL(HoaDonDAO.SQL_HOA_DON);
        db.execSQL(HoaDonChiTietDAO.SQL_HOA_DON_CHI_TIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ NguoiDungDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TheLoaiDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ SachDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ HoaDonDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ HoaDonChiTietDAO.TABLE_NAME);

        onCreate(db);
    }
}
