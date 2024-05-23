package com.example.cook_program;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
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

public class Them_Loai_Mon extends AppCompatActivity {

    LoaiMonAnDAO loaimonanDAO;
    EditText txtTenloai;
    ImageView imgHinhanh;
    Button btnUpload, btnClose;

    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_loai_mon);
        loaimonanDAO = new LoaiMonAnDAO(getApplicationContext());
        txtTenloai = (EditText) findViewById(R.id.txtTenloai);
        imgHinhanh = (ImageView) findViewById(R.id.imgHinhanh);
        imgHinhanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnClose = (Button) findViewById(R.id.btnDong);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtTenloai.getText().toString().isEmpty() && imgHinhanh.getDrawable() != null && imageToStore != null)
                {
                    loaimonanDAO.Themloaimon(new LoaiMonAn(txtTenloai.getText().toString(), imageToStore));
                    Intent intent = new Intent(Them_Loai_Mon.this, DS_Loai_Mon_An.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Them_Loai_Mon.this,"Vui lòng chọn đầy đủ thông tin để thêm", Toast.LENGTH_SHORT).show();
                }
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