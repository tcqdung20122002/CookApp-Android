package com.example.cook_program;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.cook_program.Class.LoaiMonAn;
import com.example.cook_program.DTO.LoaiMonAnDAO;
import com.example.cook_program.Database.DBHelper;

public class Cap_Nhat_Loai_Mon extends AppCompatActivity {
    LoaiMonAnDAO loaimonanDAO;
    EditText txtTenloai;
    ImageView imgHinhanh;
    Button btnUpdate, btnDelete, btnClose;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cap_nhat_loai_mon);

        loaimonanDAO = new LoaiMonAnDAO(getApplicationContext());
        txtTenloai = (EditText) findViewById(R.id.txtTenloai);
        imgHinhanh = (ImageView) findViewById(R.id.imgHinhanh);
        imgHinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, PICK_IMAGE_REQUEST);
                }
                catch (Exception e)
                {
                    Toast.makeText(Cap_Nhat_Loai_Mon.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnDong);

        Intent intent = getIntent();
        int idloaimon = intent.getIntExtra("Id_loai_mon", 0);
        String tenloaimon = intent.getStringExtra("Ten_loai_mon");

        txtTenloai.setText(tenloaimon);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtTenloai.getText().toString().isEmpty() && imgHinhanh.getDrawable() != null)
                {
                    loaimonanDAO.Capnhatloaimon(new LoaiMonAn(idloaimon,txtTenloai.getText().toString(), imageToStore));
                    Intent intent = new Intent(Cap_Nhat_Loai_Mon.this, DS_Loai_Mon_An.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Cap_Nhat_Loai_Mon.this,"Vui lòng chọn đầy đủ thông tin để thêm", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loaimonanDAO.Xoaloaimon(idloaimon);
                Intent intent = new Intent(Cap_Nhat_Loai_Mon.this, DS_Loai_Mon_An.class);
                startActivity(intent);
            }
        });
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cap_Nhat_Loai_Mon.this, DS_Loai_Mon_An.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data.getData() != null && data != null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                imgHinhanh.setImageBitmap(imageToStore);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}