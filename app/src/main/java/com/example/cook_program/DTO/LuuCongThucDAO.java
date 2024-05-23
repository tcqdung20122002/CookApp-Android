package com.example.cook_program.DTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cook_program.Class.LuuCongThuc;
import com.example.cook_program.Database.DBHelper;

public class LuuCongThucDAO {
    private SQLiteDatabase dbWritable, dbReadable;
    private DBHelper dbHelper;
    private Context context;

    public LuuCongThucDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        dbWritable = dbHelper.getWritableDatabase();
        dbReadable = dbHelper.getReadableDatabase();
    }

    public boolean Themmonyeuthich(LuuCongThuc luu)
    {
        ContentValues values = new ContentValues();
        values.put("Id_mon", luu.getId_mon());
        values.put("Id_nguoi_dung", luu.getId_nguoi_dung());
        values.put("Ngay_luu", luu.getNgay_luu());
        long kq = dbWritable.insert("Luu_cong_thuc", null, values);
        if(kq == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean Xoamonyeuthich(int Id_mon, int Id_nguoi_dung)
    {
        long kq = dbWritable.delete("Luu_cong_thuc","Id_mon = ? and Id_nguoi_dung = ?", new String[]{String.valueOf(Id_mon), String.valueOf(Id_nguoi_dung)});
        // Kiểm tra kq
        if(kq == -1)
        {
            return false; // thêm không thành công
        }
        else
        {
            return true;
        }
    }

    public boolean kiemtramondathich(int Id_mon, int Id_nguoi_dung)
    {
        Cursor c = dbReadable.rawQuery("SELECT * FROM Luu_cong_thuc Where Id_mon = ? and Id_nguoi_dung = ?", new String[]{String.valueOf(Id_mon), String.valueOf(Id_nguoi_dung)});
        if(c.getCount() > 0)
        {
            return true;
        }
        else
            return false;
    }

    public int Soluongluu(int idmon)
    {
        Cursor c = dbReadable.rawQuery("SELECT COUNT(*) FROM Luu_cong_thuc WHERE Id_mon = ?", new String[]{String.valueOf(idmon)});
        c.moveToNext();
        int soluong = c.getInt(c.getColumnIndexOrThrow("COUNT(*)"));
        return soluong;
    }

    public boolean Capnhatsoluongluu(int idmon, int soluongluu)
    {
        ContentValues values = new ContentValues();
        values.put("Sl_luu", soluongluu);
        long kq = dbWritable.update("Mon_an", values, "Id_mon_an = ?", new String[]{String.valueOf(idmon)});
        if(kq == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
