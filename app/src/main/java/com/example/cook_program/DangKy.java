package com.example.cook_program;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cook_program.DTO.NguoiDungDAO;
import com.example.cook_program.Database.DBHelper;

public class DangKy extends AppCompatActivity {

    NguoiDungDAO nguoidungDAO;
    Button btnSignup;
    EditText txtTaikhoan, txtEmail, txtMatkhau, txtXacnhanmatkhau;
    TextView txtLogintext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        nguoidungDAO = new NguoiDungDAO(getApplicationContext());
        btnSignup = (Button) findViewById(R.id.btnSignup);
        txtTaikhoan = (EditText) findViewById(R.id.txtTaikhoan);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtMatkhau = (EditText) findViewById(R.id.txtMatkhau);
        txtXacnhanmatkhau = (EditText) findViewById(R.id.txtXacnhanmatkhau);
        txtLogintext = (TextView) findViewById(R.id.txtLogintext);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentk = txtTaikhoan.getText().toString();
                String email = txtEmail.getText().toString();
                String matkhau = txtMatkhau.getText().toString();
                String xacnhanmatkhau = txtXacnhanmatkhau.getText().toString();
                if(tentk.equals("")|| email.equals("") || matkhau.equals("") || xacnhanmatkhau.equals(""))
                {
                    Toast.makeText(DangKy.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(matkhau.equals(xacnhanmatkhau))
                    {
                        boolean kiemtrataikhoan = nguoidungDAO.kiemtrataikhoan(tentk);
                        if (kiemtrataikhoan == false)
                        {
                            boolean themnguoidung = nguoidungDAO.DangKy(tentk,email,matkhau);
                            if(themnguoidung == true)
                            {
                                Toast.makeText(DangKy.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DangKy.this, DangNhap.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(DangKy.this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(DangKy.this, "Tài khoản đã tồn tại, vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(DangKy.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        txtLogintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKy.this, DangNhap.class);
                startActivity(intent);
            }
        });
    }
}