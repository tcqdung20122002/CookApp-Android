package com.example.cook_program;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cook_program.Class.LoaiMonAn;
import com.example.cook_program.Class.MonAn;
import com.example.cook_program.DTO.MonAnDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Them_Mon_An extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText txtTenmon, txtKhauphan, txtThoigiannau, txtNguyenlieu, txtHuongdan;
    Spinner spnLoai;
    ImageView imgHinhanh;
    Button btnUpload, btnDong;
    MonAnDAO monanDAO;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;

    private int idloaimon;

    ArrayList<LoaiMonAn> arr;
    ArrayAdapter<LoaiMonAn> adapter;

    Date currentDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String dateString = dateFormat.format(currentDate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_mon_an);
        monanDAO = new MonAnDAO(getApplicationContext());
        spnLoai = (Spinner) findViewById(R.id.spnLoai);
        arr = monanDAO.Laytenloaimon();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnLoai.setAdapter(adapter);
        spnLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dulieuchon = (String) spnLoai.getSelectedItem().toString();
                idloaimon = monanDAO.Layidloaimon(dulieuchon);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sharedPreferences = getSharedPreferences("DangNhap", Context.MODE_PRIVATE);
        int iduser = sharedPreferences.getInt("Id_nguoi_dung", 0);

        txtTenmon = (EditText) findViewById(R.id.txtTenmon);
        txtKhauphan = (EditText) findViewById(R.id.txtKhauphan);
        txtThoigiannau = (EditText) findViewById(R.id.txtThoigiannau);
        txtNguyenlieu  = (EditText) findViewById(R.id.txtNguyenlieu);
        txtHuongdan = (EditText) findViewById(R.id.txtHuongdan);
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
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spnLoai.getSelectedItem().toString() == null || txtTenmon.getText().toString().isEmpty() || txtKhauphan.getText().toString().isEmpty() || txtThoigiannau.getText().toString().isEmpty() || txtNguyenlieu.getText().toString().isEmpty() || imgHinhanh.getDrawable() == null || txtHuongdan.getText().toString().isEmpty())
                {
                    Toast.makeText(Them_Mon_An.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MonAn c = new MonAn();
                    c.setId_loai_mon(idloaimon);
                    c.setTen_mon(txtTenmon.getText().toString());
                    c.setKhau_phan(txtKhauphan.getText().toString());
                    c.setThoi_gian(txtThoigiannau.getText().toString());
                    c.setNguyen_lieu(txtNguyenlieu.getText().toString());
                    c.setHuong_dan(txtHuongdan.getText().toString());
                    c.setHinh_anh(imageToStore);
                    c.setId_nguoi_dang(iduser);
                    c.setNgay_dang(dateString);
                    c.setSl_luu(null);

                    boolean kq = monanDAO.Themmonan(c);
                    if(kq == false)
                    {
                        Toast.makeText(Them_Mon_An.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    else if (kq == true)
                    {
                        Toast.makeText(Them_Mon_An.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Them_Mon_An.this, DS_Mon_An.class);
                        startActivity(intent);
                    }
                }
            }
        });

        btnDong = (Button) findViewById(R.id.btnDong);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Them_Mon_An.this, DS_Mon_An.class);
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
