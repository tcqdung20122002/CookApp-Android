package com.example.cook_program.Class;

import android.graphics.Bitmap;

public class LoaiMonAn {
    private int Id_Loai_Mon;
    private String Ten_loai_mon;
    private Bitmap Hinh_anh;

    public LoaiMonAn() {}

    public LoaiMonAn(String ten_loai_mon) {
        Ten_loai_mon = ten_loai_mon;
    }

    public LoaiMonAn(String ten_loai_mon, Bitmap hinh_anh) {
        Ten_loai_mon = ten_loai_mon;
        Hinh_anh = hinh_anh;
    }

    public LoaiMonAn(int id_Loai_Mon, String ten_loai_mon, Bitmap hinh_anh) {
        Id_Loai_Mon = id_Loai_Mon;
        Ten_loai_mon = ten_loai_mon;
        Hinh_anh = hinh_anh;
    }

    public int getId_Loai_Mon() {
        return Id_Loai_Mon;
    }

    public void setId_Loai_Mon(int id_Loai_Mon) {
        Id_Loai_Mon = id_Loai_Mon;
    }

    public String getTen_loai_mon() {
        return Ten_loai_mon;
    }

    public void setTen_loai_mon(String ten_loai_mon) {
        Ten_loai_mon = ten_loai_mon;
    }

    public Bitmap getHinh_anh() {
        return Hinh_anh;
    }

    public void setHinh_anh(Bitmap hinh_anh) {
        Hinh_anh = hinh_anh;
    }

    @Override
    public String toString() {
        return Ten_loai_mon;
    }
}
