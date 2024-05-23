package com.example.cook_program;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cook_program.Class.MonAn;
import com.example.cook_program.DTO.MonAnDAO;

public class Cap_Nhat_Mon_An extends AppCompatActivity {
    MonAnDAO monanDAO;
    EditText txtId_mon_chinh, txtTen_mon_chinh, txtKhau_phan, txtThoi_gian_nau, txtLoai, txtNguyen_lieu ,txtHuong_dan;
    ImageView imgHinhanh;
    Button btnSua, btnDong, btnDelete;
    private static final int PICK_IMAGE_REQUEST = 100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cap_nhat_mon_an);
        monanDAO = new MonAnDAO(getApplicationContext());
        txtId_mon_chinh = (EditText) findViewById(R.id.txtId_mon_chinh);
        txtTen_mon_chinh = (EditText) findViewById(R.id.txtTen_mon_chinh);
        txtKhau_phan = (EditText) findViewById(R.id.txtKhau_phan);
        txtThoi_gian_nau = (EditText) findViewById(R.id.txtThoi_gian_nau);
        txtLoai = (EditText) findViewById(R.id.txtLoai);
        txtNguyen_lieu = (EditText) findViewById(R.id.txtNguyen_lieu);
        txtHuong_dan = (EditText) findViewById(R.id.txtHuong_dan);
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
                    Toast.makeText(Cap_Nhat_Mon_An.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua = (Button) findViewById(R.id.btnSua);
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtId_mon_chinh.getText().toString().isEmpty() || txtTen_mon_chinh.getText().toString().isEmpty() || txtKhau_phan.getText().toString().isEmpty() || txtThoi_gian_nau.getText().toString().isEmpty() || txtLoai.getText().toString().isEmpty() || txtNguyen_lieu.getText().toString().isEmpty() || txtHuong_dan.getText().toString().isEmpty() || imgHinhanh.getDrawable() != null)
                {
                    Toast.makeText(Cap_Nhat_Mon_An.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MonAn monan = new MonAn();
                    //monan.setId_mon_an(txtId_mon_chinh.getText().toString());
                    //monan.setTen_mon_chinh(txtTen_mon_chinh.getText().toString());
                    monan.setKhau_phan(txtKhau_phan.getText().toString());
                    //monan.setThoi_gian_nau(Integer.parseInt(txtThoi_gian_nau.getText().toString()));
                    //monan.setLoai(txtLoai.getText().toString());
                    monan.setNguyen_lieu(txtNguyen_lieu.getText().toString());
                    monan.setHuong_dan(txtHuong_dan.getText().toString());
                    monan.setHinh_anh(imageToStore);

                    //int kq = monchinhDao.SuaMonChinh(c);
                    //if(kq == -1)
                    {
                        Toast.makeText(Cap_Nhat_Mon_An.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                    //else if (kq == 1)
                    {
                        Toast.makeText(Cap_Nhat_Mon_An.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        txtId_mon_chinh.setText("");
                        txtTen_mon_chinh.setText("");
                        txtKhau_phan.setText("");
                        txtThoi_gian_nau.setText("");
                        txtLoai.setText("");
                        txtNguyen_lieu.setText("");
                        txtHuong_dan.setText("");
                        //txtHinh_anh.setText("");
                    }
                }
            }
        });
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtId_mon_chinh.getText().toString().isEmpty())
                {
                    Toast.makeText(Cap_Nhat_Mon_An.this, "Vui lòng nhập mã món để xoá", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String Id_mon_chinh = txtId_mon_chinh.getText().toString();
                    /*int kq = monchinhDao.XoaMonChinh(Id_mon_chinh);
                    if(kq == -1)
                    {
                        Toast.makeText(Cap_Nhat_Mon_An.this, "Xoá thất bại", Toast.LENGTH_SHORT).show();
                    }
                    else if (kq == 1)
                    {
                        Toast.makeText(Cap_Nhat_Mon_An.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                        txtId_mon_chinh.setText("");
                        txtTen_mon_chinh.setText("");
                        txtKhau_phan.setText("");
                        txtThoi_gian_nau.setText("");
                        txtLoai.setText("");
                        txtNguyen_lieu.setText("");
                        txtHuong_dan.setText("");
                        txtHinh_anh.setText("");
                    }
                    */
                }
            }
        });
        btnDong = (Button) findViewById(R.id.btnDong);
        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cap_Nhat_Mon_An.this, DS_Mon_An.class);
                startActivity(intent);
            }
        });

        //Lấy dữ liệu Intent từ DS_Mon_Chinh truyền lên các EditText
        Intent intent = getIntent();
        String id = intent.getStringExtra("Id_mon_chinh").toString();
        String tenmonchinh = intent.getStringExtra("Ten_mon_chinh").toString();
        String loai = intent.getStringExtra("Loai").toString();
        txtId_mon_chinh.setText(id);
        txtTen_mon_chinh.setText(tenmonchinh);
        txtLoai.setText(loai);
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
