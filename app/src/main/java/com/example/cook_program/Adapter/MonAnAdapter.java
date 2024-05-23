package com.example.cook_program.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cook_program.Class.LuuCongThuc;
import com.example.cook_program.Class.MonAn;
import com.example.cook_program.DTO.LuuCongThucDAO;
import com.example.cook_program.Database.DBHelper;
import com.example.cook_program.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder> {

    LuuCongThucDAO luucongthucDAO;
    Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    String currentdate = sdf.format(new Date());
    private int iduser;
    private boolean kiemtra;
    ArrayList<MonAn> Monan;

    public MonAnAdapter(ArrayList<MonAn> Monan, Context context, int iduser)
    {
        this.Monan = Monan;
        this.context = context;
        this.iduser = iduser;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mon_an, parent, false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        MonAn monan = Monan.get(position);
        if(monan == null)
        {
            return;
        }

        boolean kiemtramondathich = luucongthucDAO.kiemtramondathich(monan.getId_mon_an(), iduser);
        if(kiemtramondathich == true)
        {
            holder.btnLuu.setBackgroundResource(R.drawable.ic_favorite_red_foreground);
        }
        else
        {
            holder.btnLuu.setBackgroundResource(R.drawable.ic_favorite_black_foreground);
        }
        holder.txtThoigian.setText(String.valueOf(monan.getId_mon_an()));
        holder.imgMonan.setImageBitmap(monan.getHinh_anh());

        holder.btnLuu.setChecked(kiemtramondathich);
        holder.btnLuu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                int soluongluu = luucongthucDAO.Soluongluu(monan.getId_mon_an());
                if(isChecked == false)
                {
                    luucongthucDAO.Themmonyeuthich(new LuuCongThuc(monan.getId_mon_an(), iduser, currentdate));
                    luucongthucDAO.Capnhatsoluongluu(monan.getId_mon_an(), soluongluu + 1);
                    holder.btnLuu.setBackgroundResource(R.drawable.ic_favorite_red_foreground);
                    holder.txtTenmon.setText(String.valueOf(soluongluu));
                }
                else
                {
                    luucongthucDAO.Xoamonyeuthich(monan.getId_mon_an(), iduser);
                    luucongthucDAO.Capnhatsoluongluu(monan.getId_mon_an(), soluongluu - 1);
                    holder.btnLuu.setBackgroundResource(R.drawable.ic_favorite_black_foreground);
                    holder.txtTenmon.setText(String.valueOf(soluongluu));
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        if(Monan == null)
        {
            return 0;
        }
        return Monan.size();
    }

    public class MonAnViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMonan;
        private TextView txtTenmon, txtThoigian;
        private ToggleButton btnLuu;
        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMonan = itemView.findViewById(R.id.imgMonan);
            txtTenmon = itemView.findViewById(R.id.txtTenmon);
            txtThoigian = itemView.findViewById(R.id.txtThoigian);
            btnLuu = itemView.findViewById(R.id.btnLuu);
            luucongthucDAO = new LuuCongThucDAO(context.getApplicationContext());
        }
    }
}
