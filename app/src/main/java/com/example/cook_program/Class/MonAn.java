package com.example.cook_program.Class;

import android.graphics.Bitmap;

public class MonAn {
    private int Id_mon_an;
    private int Id_loai_mon;
    private String Ten_mon;
    private String Khau_phan;
    private String Thoi_gian;
    private String Nguyen_lieu;
    private String Huong_dan;
    private Bitmap Hinh_anh;
    private int Id_nguoi_dang;
    private String Ngay_dang;
    private String Sl_luu;

    public MonAn() {}

    public MonAn(int id_mon_an, String ten_mon, String thoi_gian, Bitmap hinh_anh) {
        Id_mon_an = id_mon_an;
        Ten_mon = ten_mon;
        Thoi_gian = thoi_gian;
        Hinh_anh = hinh_anh;
    }

    public MonAn(int id_mon_an, int id_loai_mon, String ten_mon, String khau_phan, String thoi_gian, String nguyen_lieu, String huong_dan, Bitmap hinh_anh, int id_nguoi_dang, String ngay_dang, String sl_luu) {
        Id_mon_an = id_mon_an;
        Id_loai_mon = id_loai_mon;
        Ten_mon = ten_mon;
        Khau_phan = khau_phan;
        Thoi_gian = thoi_gian;
        Nguyen_lieu = nguyen_lieu;
        Huong_dan = huong_dan;
        Hinh_anh = hinh_anh;
        Id_nguoi_dang = id_nguoi_dang;
        Ngay_dang = ngay_dang;
        Sl_luu = sl_luu;
    }

    public int getId_mon_an() {
        return Id_mon_an;
    }

    public void setId_mon_an(int id_mon_an) {
        Id_mon_an = id_mon_an;
    }

    public int getId_loai_mon() {
        return Id_loai_mon;
    }

    public void setId_loai_mon(int id_loai_mon) {
        Id_loai_mon = id_loai_mon;
    }

    public String getTen_mon() {
        return Ten_mon;
    }

    public void setTen_mon(String ten_mon) {
        Ten_mon = ten_mon;
    }

    public String getKhau_phan() {
        return Khau_phan;
    }

    public void setKhau_phan(String khau_phan) {
        Khau_phan = khau_phan;
    }

    public String getThoi_gian() {
        return Thoi_gian;
    }

    public void setThoi_gian(String thoi_gian) {
        Thoi_gian = thoi_gian;
    }

    public String getNguyen_lieu() {
        return Nguyen_lieu;
    }

    public void setNguyen_lieu(String nguyen_lieu) {
        Nguyen_lieu = nguyen_lieu;
    }

    public String getHuong_dan() {
        return Huong_dan;
    }

    public void setHuong_dan(String huong_dan) {
        Huong_dan = huong_dan;
    }

    public Bitmap getHinh_anh() {
        return Hinh_anh;
    }

    public void setHinh_anh(Bitmap hinh_anh) {
        Hinh_anh = hinh_anh;
    }

    public int getId_nguoi_dang() {
        return Id_nguoi_dang;
    }

    public void setId_nguoi_dang(int id_nguoi_dang) {
        Id_nguoi_dang = id_nguoi_dang;
    }

    public String getNgay_dang() {
        return Ngay_dang;
    }

    public void setNgay_dang(String ngay_dang) {
        Ngay_dang = ngay_dang;
    }

    public String getSl_luu() {
        return Sl_luu;
    }

    public void setSl_luu(String sl_luu) {
        Sl_luu = sl_luu;
    }
}
