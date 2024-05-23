package com.example.cook_program.DTO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.cook_program.Class.LoaiMonAn;
import com.example.cook_program.Class.MonAn;
import com.example.cook_program.Database.DBHelper;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MonAnDAO {
    private SQLiteDatabase dbWritable, dbReadable;
    private DBHelper dbHelper;
    private Context context;
    private ByteArrayOutputStream obj;
    private byte[] imageInBytes;

    public MonAnDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        dbWritable = dbHelper.getWritableDatabase();
        dbReadable = dbHelper.getReadableDatabase();
    }

    public boolean Themmonan(MonAn monan)
    {
        Bitmap image = monan.getHinh_anh();
        obj = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, obj);
        imageInBytes = obj.toByteArray();

        ContentValues values = new ContentValues();
        values.put("Id_loai_mon", monan.getId_loai_mon());
        values.put("Ten_mon", monan.getTen_mon());
        values.put("Khau_phan", monan.getKhau_phan());
        values.put("Thoi_gian", monan.getThoi_gian());
        values.put("Nguyen_lieu", monan.getNguyen_lieu());
        values.put("Huong_dan", monan.getHuong_dan());
        values.put("Hinh_anh", imageInBytes);
        values.put("Id_nguoi_dang", monan.getId_nguoi_dang());
        values.put("Ngay_dang", monan.getNgay_dang());
        values.put("Sl_luu", monan.getSl_luu());

        long kq = dbWritable.insert("Mon_an", null, values);
        if(kq == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public ArrayList<LoaiMonAn> Laytenloaimon()
    {
        ArrayList<LoaiMonAn> arr = new ArrayList<>();
        Cursor c = dbReadable.rawQuery("SELECT Ten_loai_mon FROM Loai_mon_an", null);
        if(c.getCount() != 0)
        {
            while(c.moveToNext())
            {
                String tenloaimon = c.getString(0);
                arr.add(new LoaiMonAn(tenloaimon));
            }
            return arr;
        }
        else
        {
            Toast.makeText(context, "Dữ liệu bị rỗng", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public int Layidloaimon (String tenloai)
    {
        Cursor c = dbReadable.rawQuery("SELECT Id_loai_mon FROM Loai_mon_an WHERE Ten_loai_mon = ?", new String[]{tenloai});
        c.moveToNext();
        int idloai = c.getInt(0);
        return idloai;
    }

    public ArrayList<MonAn> Laymonan()
    {
        ArrayList<MonAn> arr = new ArrayList<>();
        Cursor c = dbReadable.rawQuery("SELECT * FROM Mon_an", null);
        if(c.getCount() != 0)
        {
            while(c.moveToNext())
            {
                int idmon = Integer.parseInt(c.getString(0));
                String tenmon = c.getString(2);
                String thoigian = c.getString(4);
                byte[] hinhanh = c.getBlob(7);

                Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
                arr.add(new MonAn(idmon,tenmon,thoigian,bitmap));
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
