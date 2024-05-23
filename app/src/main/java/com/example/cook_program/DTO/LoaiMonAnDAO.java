package com.example.cook_program.DTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.cook_program.Class.LoaiMonAn;
import com.example.cook_program.Database.DBHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class LoaiMonAnDAO {
    private SQLiteDatabase dbWritable, dbReadable;
    private DBHelper dbHelper;
    private Context context;

    private ByteArrayOutputStream obj;
    private byte[] imageInBytes;

    public LoaiMonAnDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        dbWritable = dbHelper.getWritableDatabase();
        dbReadable = dbHelper.getReadableDatabase();
    }

    public boolean Themloaimon(LoaiMonAn loai)
    {
        Bitmap image = loai.getHinh_anh();
        obj = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, obj);
        imageInBytes = obj.toByteArray();

        ContentValues values = new ContentValues();
        values.put("Ten_loai_mon", loai.getTen_loai_mon());
        values.put("Hinh_anh", imageInBytes);
        long kq = dbWritable.insert("Loai_mon_an", null, values);
        if(kq == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean Capnhatloaimon(LoaiMonAn loai)
    {
        ContentValues values = new ContentValues();
        Bitmap image = loai.getHinh_anh();
        obj = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, obj);
        imageInBytes = obj.toByteArray();
        values.put("Ten_loai_mon", loai.getTen_loai_mon());
        values.put("Hinh_anh", imageInBytes);
        long kq = dbWritable.update("Loai_mon_an", values, "Id_loai_mon=?", new String[]{String.valueOf(loai.getId_Loai_Mon())});
        if(kq == -1)
        {
            return false;
        }
        else
            return true;
    }

    public boolean Xoaloaimon(int Id_loai_mon)
    {
        long kq = dbWritable.delete("Loai_mon_an","Id_loai_mon=?", new String[]{String.valueOf(Id_loai_mon)});
        if(kq == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public ArrayList<LoaiMonAn> Layloaimon()
    {
        ArrayList<LoaiMonAn> arr = new ArrayList<>();
        Cursor c = dbReadable.rawQuery("SELECT * FROM Loai_mon_an", null);
        if(c.getCount() != 0)
        {
            while(c.moveToNext())
            {
                int id = Integer.parseInt(c.getString(0));
                String tenloaimon = c.getString(1);
                byte[] hinhanh = c.getBlob(2);

                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
                arr.add(new LoaiMonAn(id, tenloaimon, bitmap));
            }
            return arr;
        }
        else
        {
            Toast.makeText(context, "Dữ liệu bị rỗng", Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
