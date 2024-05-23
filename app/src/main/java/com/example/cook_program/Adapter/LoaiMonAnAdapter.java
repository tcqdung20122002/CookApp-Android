package com.example.cook_program.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cook_program.Cap_Nhat_Loai_Mon;
import com.example.cook_program.Class.LoaiMonAn;
import com.example.cook_program.Database.DBHelper;
import com.example.cook_program.R;

import java.util.ArrayList;

public class LoaiMonAnAdapter extends RecyclerView.Adapter<LoaiMonAnAdapter.LoaiMonAnViewHolder> {

    ArrayList<LoaiMonAn> arr;
    Context context;

    public LoaiMonAnAdapter(ArrayList<LoaiMonAn> arr, Context context) {
        this.arr = arr;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiMonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_mon, parent, false);
        return new LoaiMonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiMonAnViewHolder holder, int position) {
        LoaiMonAn loaimonan = arr.get(position);
        if(loaimonan == null)
        {
            return;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, Cap_Nhat_Loai_Mon.class);
                int id = loaimonan.getId_Loai_Mon();
                String tenmonan = holder.txtTenloai.getText().toString();
                intent.putExtra("Id_loai_mon", id);
                intent.putExtra("Ten_loai_mon", tenmonan);
                context.startActivity(intent);
            }
        });
        //holder.txtTenloai.setText(String.valueOf(loaimonan.getId_Loai_Mon()));
        holder.txtTenloai.setText(loaimonan.getTen_loai_mon());
        holder.imgLoaimon.setImageBitmap(loaimonan.getHinh_anh());
    }

    @Override
    public int getItemCount() {
        if(arr != null)
        {
            return arr.size();
        }
        return 0;
    }

    public class LoaiMonAnViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgLoaimon;
        private TextView txtTenloai;

        public LoaiMonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLoaimon = itemView.findViewById(R.id.imgLoaimon);
            txtTenloai = itemView.findViewById(R.id.txtTenloai);
        }
    }
}
