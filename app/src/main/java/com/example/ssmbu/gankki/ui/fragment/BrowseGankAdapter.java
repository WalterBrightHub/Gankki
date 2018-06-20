package com.example.ssmbu.gankki.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.service.entity.GankItem;
import com.example.ssmbu.gankki.ui.activity.BrowserActivity;

import java.util.ArrayList;
import java.util.List;

public class BrowseGankAdapter extends RecyclerView.Adapter<BrowseGankAdapter.RecVh> {
    private static final String TAG = "BrowseGankAdapter";
    List<GankItem> mGankItems = new ArrayList<>();
    Fragment mFragment;

    public BrowseGankAdapter(List<GankItem> gankItems, Fragment fragment){
        mGankItems=gankItems;
        mFragment=fragment;
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
    public void onBindViewHolder(@NonNull final RecVh holder, int position) {
        final GankItem gankItem= mGankItems.get(position);
        holder.mDesc.setText(gankItem.getDesc());
        String via=gankItem.getWho()+" 晾晒于 "+gankItem.getPublishedAt().substring(0,10);
        holder.mVia.setText(via);

        if(gankItem.getType().equals("福利")){
            Glide.with(mFragment)
                    .load(gankItem.getUrl())
                    .into(holder.mMeizi);
            holder.itemView.setOnClickListener(null);
            holder.mDesc.setVisibility(View.GONE);
            holder.mMeizi.setVisibility(View.VISIBLE);
        }
        else {
            holder.mDesc.setVisibility(View.VISIBLE);
            holder.mMeizi.setVisibility(View.GONE);
            Glide.with(mFragment).clear(holder.mMeizi);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(gankItem.getType().equals("休息视频")){
                        //太复杂了，直接打开浏览器吧
                        Intent intent=new Intent();
                        intent.setData(Uri.parse(gankItem.getUrl()));
                        intent.setAction("android.intent.action.VIEW");
                        mFragment.startActivity(intent);
                    }
                    else {
                        Intent intent=new Intent(mFragment.getContext(), BrowserActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //intent.putExtra("url",gankItem.getUrl());
                        //intent.putExtra("desc",gankItem.getDesc());
                        //intent.putExtra("_id",gankItem.get_id());
                        intent.putExtra("gankItem_data",gankItem);
                        mFragment.startActivity(intent);
                    }

                }
            });
        }

    }

    @Override
    public void onViewRecycled(@NonNull RecVh holder) {
        Glide.with(mFragment).clear(holder.mMeizi);
        super.onViewRecycled(holder);
    }

    public class RecVh extends RecyclerView.ViewHolder {
        TextView mDesc;
        TextView mVia;
        ImageView mMeizi;
        public RecVh(View itemView) {
            super(itemView);
            mDesc=itemView.findViewById(R.id.desc);
            mVia=itemView.findViewById(R.id.via);
            mMeizi=itemView.findViewById(R.id.meizi);
        }
    }


}
