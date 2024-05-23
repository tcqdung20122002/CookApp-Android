package com.example.cook_program.DTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cook_program.Database.DBHelper;

public class NguoiDungDAO {
    private SQLiteDatabase dbWritable, dbReadable;
    private DBHelper dbHelper;
    private Context context;

    public NguoiDungDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        dbWritable = dbHelper.getWritableDatabase();
        dbReadable = dbHelper.getReadableDatabase();
    }

    public boolean DangKy(String tentk, String email ,String matkhau)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", email);
        contentValues.put("Tai_Khoan", tentk);
        contentValues.put("Mat_khau", matkhau);
        long result = dbWritable.insert("Nguoi_dung", null, contentValues);
        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }

    public boolean kiemtrataikhoan(String taikhoan)
    {
        Cursor c = dbReadable.rawQuery("Select * from Nguoi_dung where Tai_khoan = ?", new String[]{taikhoan});
        if(c.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean kiemtrataikhoanmatkhau(String taikhoan, String matkhau)
    {
        Cursor c = dbReadable.rawQuery("Select * from Nguoi_dung where Tai_khoan = ? and Mat_khau = ?", new String[]{taikhoan, matkhau});
        if(c.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int Layiduser(String tentk)
    {
        Cursor c = dbReadable.rawQuery("SELECT Id_nguoi_dung FROM Nguoi_dung WHERE Tai_khoan = ?", new String[]{tentk});
        c.moveToNext();
        int iduser = c.getInt(0);
        return iduser;
    }
}
