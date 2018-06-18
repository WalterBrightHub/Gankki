package com.example.ssmbu.gankki.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.service.entity.GankItem;
import com.example.ssmbu.gankki.service.presenter.GankPresenter;
import com.example.ssmbu.gankki.service.view.GankView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GankListFragment extends Fragment {
    private static final String TAG = "GankListFragment";

    private String mTag;


    public static GankListFragment newInstance(String tag){
        final GankListFragment gankListFragment=new GankListFragment();
        final Bundle args=new Bundle();
        args.putString("tag",tag);
        gankListFragment.setArguments(args);
        return  gankListFragment;
    }

    private GankPresenter mGankPresenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args=getArguments();
        mTag=(String) args.getString("tag","all");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_gank_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mGankPresenter=new GankPresenter(mGankView);
        //mGankPresenter.getGanksByDate("2018","6","15");
        mGankPresenter.getGanksByTag(mTag,"20","1");
        swipeRefreshLayout.setRefreshing(true);
        //Log.d(TAG, "onCreateView: "+new Date().toString());
    }

    private void initView(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mGankPresenter.getGanksByTag(mTag,"20","1");
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private boolean firstload=true;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private GankView mGankView=new GankView() {
        @Override
        public void showGankItems(List<GankItem> gankItems) {
            recyclerView.setAdapter(new RecyclerAdapter(gankItems,GankListFragment.this));
            Log.d(TAG, "showGankItems: "+gankItems.size());
        }

        @Override
        public void getGankComplete() {
            swipeRefreshLayout.setRefreshing(false);
            if(firstload){
                firstload=false;
                return;
            }
            Toast.makeText(getContext(),"加载完成", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void getGankError() {

        }
    };
}
