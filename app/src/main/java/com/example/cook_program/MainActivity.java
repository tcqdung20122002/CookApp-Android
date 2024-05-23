package com.example.cook_program;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cook_program.Class.MonAn;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button btnMon_chinh, btnMonan;
    TextView txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("DangNhap", Context.MODE_PRIVATE);
        int iduser = sharedPreferences.getInt("Id_nguoi_dung", 0);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtEmail.setText(String.valueOf(iduser));
        btnMon_chinh = (Button) findViewById(R.id.btnMonchinh);
        btnMon_chinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DS_Loai_Mon_An.class);
                startActivity(intent);
            }
        });
        btnMonan = (Button) findViewById(R.id.btnMonan);
        btnMonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DS_Mon_An.class);
                startActivity(intent);
            }
        });
    }
}