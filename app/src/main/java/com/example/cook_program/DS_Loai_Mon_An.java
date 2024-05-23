package com.example.cook_program;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cook_program.Adapter.LoaiMonAnAdapter;
import com.example.cook_program.DTO.LoaiMonAnDAO;
import com.example.cook_program.Database.DBHelper;

public class DS_Loai_Mon_An extends AppCompatActivity {

    LoaiMonAnDAO loaimonanDAO;
    RecyclerView recycler;
    LoaiMonAnAdapter loaiadapter;
    Button btnInsert, btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ds_loai_mon_an);
        loaimonanDAO = new LoaiMonAnDAO(getApplicationContext());
        recycler = (RecyclerView) findViewById(R.id.rcyLoaimon);
        loaiadapter = new LoaiMonAnAdapter(loaimonanDAO.Layloaimon(), DS_Loai_Mon_An.this);
        recycler.setHasFixedSize(true);
        GridLayoutManager grid = new GridLayoutManager(this, 2);
        recycler.setLayoutManager(grid);
        recycler.setAdapter(loaiadapter);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnClose = (Button) findViewById(R.id.btnDong);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DS_Loai_Mon_An.this, Them_Loai_Mon.class);
                startActivity(intent);
            }
        });

    }
}
