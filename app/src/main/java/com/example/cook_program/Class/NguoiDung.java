package com.example.cook_program.Class;

public class NguoiDung {
    private int Id_nguoi_dung;
    private String Email;
    private String Tai_khoan;
    private String Mat_khau;

    public NguoiDung() {}

    public NguoiDung(int id_nguoi_dung, String email, String tai_khoan, String mat_khau) {
        Id_nguoi_dung = id_nguoi_dung;
        Email = email;
        Tai_khoan = tai_khoan;
        Mat_khau = mat_khau;
    }

    public int getId_nguoi_dung() {
        return Id_nguoi_dung;
    }

    public void setId_nguoi_dung(int id_nguoi_dung) {
        Id_nguoi_dung = id_nguoi_dung;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTai_khoan() {
        return Tai_khoan;
    }

    public void setTai_khoan(String tai_khoan) {
        Tai_khoan = tai_khoan;
    }

    public String getMat_khau() {
        return Mat_khau;
    }

    public void setMat_khau(String mat_khau) {
        Mat_khau = mat_khau;
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "Id_nguoi_dung=" + Id_nguoi_dung +
                ", Email='" + Email + '\'' +
                ", Tai_khoan='" + Tai_khoan + '\'' +
                ", Mat_khau='" + Mat_khau + '\'' +
                '}';
    }
}
