package com.example.cook_program.Class;

public class LuuCongThuc {
    private int Id_mon;
    private int Id_nguoi_dung;
    private String Ngay_luu;

    public LuuCongThuc() {}

    public LuuCongThuc(int id_mon, int id_nguoi_dung, String ngay_luu) {
        Id_mon = id_mon;
        Id_nguoi_dung = id_nguoi_dung;
        Ngay_luu = ngay_luu;
    }

    public int getId_mon() {
        return Id_mon;
    }

    public void setId_mon(int id_mon) {
        Id_mon = id_mon;
    }

    public int getId_nguoi_dung() {
        return Id_nguoi_dung;
    }

    public void setId_nguoi_dung(int id_nguoi_dung) {
        Id_nguoi_dung = id_nguoi_dung;
    }

    public String getNgay_luu() {
        return Ngay_luu;
    }

    public void setNgay_luu(String ngay_luu) {
        Ngay_luu = ngay_luu;
    }

    @Override
    public String toString() {
        return "LuuCongThuc{" +
                "Id_mon=" + Id_mon +
                ", Id_nguoi_dung=" + Id_nguoi_dung +
                ", Ngay_luu='" + Ngay_luu + '\'' +
                '}';
    }
}
