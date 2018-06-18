package com.example.ssmbu.gankki.ui.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.service.entity.GankItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecVh> {
    List<GankItem> mGankItems = new ArrayList<>();

    public RecyclerAdapter(List<GankItem> gankItems){
        mGankItems=gankItems;
    }

    @NonNull
    @Override
    public RecVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false);
        return new RecVh(view);
    }

    @Override
    public int getItemCount() {
        return mGankItems.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecVh holder, int position) {
        GankItem gankItem= mGankItems.get(position);
        holder.mDesc.setText(gankItem.getDesc());
        String via=gankItem.getWho()+" 晾晒于 "+gankItem.getPublishedAt().substring(0,10);
        holder.mVia.setText(via);
    }

    public class RecVh extends RecyclerView.ViewHolder {
        TextView mDesc;
        TextView mVia;
        public RecVh(View itemView) {
            super(itemView);
            mDesc=itemView.findViewById(R.id.desc);
            mVia=(TextView)itemView.findViewById(R.id.via);
        }
    }
}
