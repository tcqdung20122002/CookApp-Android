package com.example.cook_program.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.core.database.sqlite.SQLiteDatabaseKt;

import com.example.cook_program.Class.LoaiMonAn;
import com.example.cook_program.Class.LuuCongThuc;
import com.example.cook_program.Class.MonAn;
import com.example.cook_program.Them_Loai_Mon;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    Context context;
    public static final String CREATE_TB_LOAIMONAN = "CREATE TABLE Loai_mon_an(Id_loai_mon INTEGER PRIMARY KEY AUTOINCREMENT, Ten_loai_mon TEXT, Hinh_anh BLOB)";
    public static final String CREATE_TB_NGUOIDUNG = "CREATE TABLE Nguoi_dung(Id_nguoi_dung INTEGER PRIMARY KEY AUTOINCREMENT, Email TEXT, Tai_khoan TEXT, Mat_khau TEXT)";
    public static final String CREATE_TB_MONAN = "CREATE TABLE Mon_an(Id_mon_an INTEGER PRIMARY KEY AUTOINCREMENT, Id_loai_mon INTEGER REFERENCES Loai_mon_an(Id_loai_mon), Ten_mon TEXT, Khau_phan TEXT, Thoi_gian TEXT, Nguyen_lieu TEXT, Huong_dan TEXT, Hinh_anh BLOB, Id_nguoi_dang INTEGER REFERENCES Nguoi_dung(Id_nguoi_dung), Ngay_dang TEXT, Sl_luu INTEGER)";
    public static final String CREATE_TB_LUUCONGTHUC = "CREATE TABLE Luu_cong_thuc(Id_mon INTEGER REFERENCES Mon_an(Id_mon_an), Id_nguoi_dung INTEGER REFERENCES Nguoi_dung(Id_nguoi_dung), Ngay_luu TEXT, PRIMARY KEY(Id_mon, Id_nguoi_dung))";

    public DBHelper(Context context)
    {
        super(context, "Cook_Program.db" , null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TB_LOAIMONAN);
        sqLiteDatabase.execSQL(CREATE_TB_NGUOIDUNG);
        sqLiteDatabase.execSQL(CREATE_TB_MONAN);
        sqLiteDatabase.execSQL(CREATE_TB_LUUCONGTHUC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Loai_mon_an");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Nguoi_dung");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Mon_an");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Luu_cong_thuc");
    }
}
