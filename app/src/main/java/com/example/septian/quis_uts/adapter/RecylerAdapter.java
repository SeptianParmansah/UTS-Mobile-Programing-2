package com.example.septian.quis_uts.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.septian.quis_uts.MainActivity;
import com.example.septian.quis_uts.R;
import com.example.septian.quis_uts.model.DataModel;

import java.util.List;

/**
 * Created by M. Septian Parmansah
 * Created by Server on 03/05/2019.
 */

public class RecylerAdapter extends
        RecyclerView.Adapter<RecylerAdapter.MyHolder> {
            List<DataModel> mList ;
            Context ctx;

    public RecylerAdapter(Context ctx, List<DataModel> mList) {
        this.mList = mList;
        this.ctx = ctx;
    }

    @Override
    public RecylerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist, parent, false);
        MyHolder holder = new MyHolder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecylerAdapter.MyHolder holder, final int position) {
        holder.kode_toko.setText(mList.get(position).getKode_toko());
        holder.nama_toko.setText(mList.get(position).getNama_toko());
        holder.alamat_toko.setText(mList.get(position).getAlamat_toko());
        holder.pemilik_toko.setText(mList.get(position).getPemilik_toko());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(ctx,MainActivity.class);
                try {
                    goInput.putExtra("id", mList.get(position).getId());
                    goInput.putExtra("kode_toko", mList.get(position).getKode_toko());
                    goInput.putExtra("nama_toko", mList.get(position).getNama_toko());
                    goInput.putExtra("alamat_toko", mList.get(position).getAlamat_toko());
                    goInput.putExtra("pemilik_toko", mList.get(position).getPemilik_toko());

                    ctx.startActivity(goInput);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(ctx, "Error data " +e,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        }

    @Override
    public int getItemCount()
    {
        return mList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        TextView kode_toko, nama_toko, alamat_toko, pemilik_toko;
        DataModel dataModel;
        public MyHolder(View v)
        {
            super(v);
            kode_toko       = (TextView) v.findViewById(R.id.Kd_toko)
            nama_toko       = (TextView) v.findViewById(R.id.Nm_toko);
            alamat_toko     = (TextView) v.findViewById(R.id.Almt_toko);
            pemilik_toko    = (TextView) v.findViewById(R.id.Pmlk_toko);
        }
        }
    }