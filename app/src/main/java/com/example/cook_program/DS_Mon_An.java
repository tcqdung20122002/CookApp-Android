package com.example.cook_program;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cook_program.Adapter.MonAnAdapter;
import com.example.cook_program.DTO.MonAnDAO;
import com.example.cook_program.Database.DBHelper;


public class DS_Mon_An extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button btnDong, btnInsert;
    RecyclerView recycle;
    MonAnAdapter monanadapter;
    MonAnDAO monanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ds_mon_an);
        sharedPreferences = getSharedPreferences("DangNhap", Context.MODE_PRIVATE);
        int iduser = sharedPreferences.getInt("Id_nguoi_dung", 0);

        monanDao = new MonAnDAO(getApplicationContext());
        recycle = (RecyclerView) findViewById(R.id.rcyMonan);
        monanadapter = new MonAnAdapter(monanDao.Laymonan(), DS_Mon_An.this, iduser);
        recycle.setHasFixedSize(true);
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recycle.setLayoutManager(grid);
        recycle.setAdapter(monanadapter);

        btnDong = (Button) findViewById(R.id.btnDong);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DS_Mon_An.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DS_Mon_An.this, Them_Mon_An.class);
                startActivity(intent);
            }
        });
    }
}
