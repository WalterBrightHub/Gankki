package com.example.ssmbu.gankki.ui.fragment;



import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.annotation.AnnotationGank;
import com.example.ssmbu.gankki.service.entity.GankItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<GankItem> mGankItems;
    Fragment mFragment;

    public GankAdapter(List<GankItem> gankItems,Fragment fragment){
        mGankItems=gankItems;
        mFragment=fragment;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GankItem gankItem=mGankItems.get(position);
        String via=gankItem.getWho()+" 晾晒于 "+gankItem.getPublishedAt().substring(0,10);
        if(holder instanceof AndroidViewHolder){
            ((AndroidViewHolder) holder).mDesc.setText(gankItem.getDesc());

            ((AndroidViewHolder) holder).mVia.setText(via);
        }
        else if(holder instanceof FeastViewHolder){
            Glide.with(mFragment)
                    .load(gankItem.getUrl())
                    .into(((FeastViewHolder) holder).mMeizi);
            ((FeastViewHolder) holder).mVia.setText(via);

        }
    }

    @Override
    public void onViewRecycled(@NonNull RecyclerView.ViewHolder holder) {
        if(holder instanceof FeastViewHolder){
            Glide.with(mFragment)
                    .clear(((FeastViewHolder) holder).mMeizi);
        }
        super.onViewRecycled(holder);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        if(viewType==AnnotationGank.ANDROID){
            return new AndroidViewHolder(inflater.inflate(R.layout.item_android,parent,false));
        }else if(viewType==AnnotationGank.FEAST_FOR_EYES){
            return new FeastViewHolder(inflater.inflate(R.layout.item_feast,parent,false));
        }
        else {
            return new AndroidViewHolder(inflater.inflate(R.layout.item_android,parent,false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        String type=mGankItems.get(position).getType();
        int itemViewType;
        switch (type){
            case "Android":
                itemViewType= AnnotationGank.ANDROID;
                break;
            case "iOS":
                itemViewType=AnnotationGank.IOS;
                break;
            case "App":
                itemViewType=AnnotationGank.APP;
                break;
            case "前端":
                itemViewType=AnnotationGank.FRONT_END;
                break;
            case "瞎推荐":
                itemViewType=AnnotationGank.BLIND;
                break;
            case "拓展资源":
                itemViewType=AnnotationGank.EXPANDING_RESOURCE;
                break;
            case "休息视频":
                itemViewType=AnnotationGank.RELAXED_VEDIO;
                break;
            case "福利":
                itemViewType=AnnotationGank.FEAST_FOR_EYES;
                break;
            default:
                itemViewType=AnnotationGank.ALL;
        }
        return itemViewType;
    }

    @Override
    public int getItemCount() {
        return mGankItems.size();
    }

    class AndroidViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.desc)
        TextView mDesc;
        @BindView(R.id.via)
        TextView mVia;
        public AndroidViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    class FeastViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.meizi)
        ImageView mMeizi;
        @BindView(R.id.via)
        TextView mVia;
        public FeastViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
