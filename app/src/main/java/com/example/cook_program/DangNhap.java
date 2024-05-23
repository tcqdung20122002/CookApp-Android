package com.example.cook_program;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cook_program.DTO.NguoiDungDAO;
import com.example.cook_program.Database.DBHelper;

public class DangNhap extends AppCompatActivity {

    NguoiDungDAO nguoidungDAO;
    Button btnLogin;
    EditText txtTaikhoan, txtMatkhau;
    TextView txtRegisterText;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        sharedPreferences = getSharedPreferences("DangNhap", Context.MODE_PRIVATE);
        nguoidungDAO = new NguoiDungDAO(getApplicationContext());
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtTaikhoan = (EditText) findViewById(R.id.txtTaikhoan);
        txtMatkhau = (EditText) findViewById(R.id.txtMatkhau);
        txtRegisterText = (TextView) findViewById(R.id.txtRegistertext);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentk = txtTaikhoan.getText().toString();
                String matkhau = txtMatkhau.getText().toString();
                if(tentk.equals("") || matkhau.equals(""))
                {
                    Toast.makeText(DangNhap.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean kiemtrataikhoan = nguoidungDAO.kiemtrataikhoanmatkhau(tentk, matkhau);
                    if(kiemtrataikhoan == true)
                    {
                        Toast.makeText(DangNhap.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        int iduser = nguoidungDAO.Layiduser(txtTaikhoan.getText().toString());
                        editor.putInt("Id_nguoi_dung", iduser);
                        editor.apply();
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        txtRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
    }
}