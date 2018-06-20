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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchGankRVFragment extends Fragment {
    private static final String TAG = "BrowseGankRVFragment";
    //一次加载20条数据
    private static final String COUNT = "20";
    //从第一页开始加载
    private int mPage=1;
    //初始化时加载
    private boolean isFirstLoad =true;
    //返回少于20条时停止加载
    boolean isLastLoad=false;

    private String mTag;
    private String mKeyword;
    private List<GankItem> mGankItems =new ArrayList<>();
    private BrowseGankAdapter mBrowseGankAdapter;


    public static SearchGankRVFragment newInstance(String keyword,String tag){
        final SearchGankRVFragment searchGankRVFragment =new SearchGankRVFragment();
        final Bundle args=new Bundle();
        args.putString("keyword",keyword);
        args.putString("tag",tag);
        searchGankRVFragment.setArguments(args);
        return searchGankRVFragment;
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
        mTag=args.getString("tag","all");
        mKeyword=args.getString("keyword","gank");
        Log.d(TAG, "onCreate: keyword "+mKeyword);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_browse_gank_rv, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mGankPresenter=new GankPresenter(mGankView);
        mGankPresenter.getGanksByKeyword(mKeyword,mTag,COUNT,String.valueOf(mPage++));
        //mGankPresenter.getGanksByTag(mTag,COUNT,String.valueOf(mPage++));
        swipeRefreshLayout.setRefreshing(true);
    }

    private void initView(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage=1;
                mGankItems.clear();
                //会闪烁，体验不好
                //mBrowseGankAdapter.notifyDataSetChanged();
                mGankPresenter.getGanksByKeyword(mKeyword,mTag,COUNT,String.valueOf(mPage++));
                //mGankPresenter.getGanksByTag(mTag,COUNT,String.valueOf(mPage++));
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBrowseGankAdapter =new BrowseGankAdapter(mGankItems,SearchGankRVFragment.this);
        recyclerView.setAdapter(mBrowseGankAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    if(!recyclerView.canScrollVertically(1)){

                        mGankPresenter.getGanksByKeyword(mKeyword,mTag,COUNT,String.valueOf(mPage++));
                       // mGankPresenter.getGanksByTag(mTag,COUNT,String.valueOf(mPage++));
                        //Log.d(TAG, "onScrollStateChanged: "+mPage);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private GankView mGankView=new GankView() {
        @Override
        public void showGankItems(List<GankItem> gankItems) {
            mGankItems.addAll(gankItems);
            mBrowseGankAdapter.notifyDataSetChanged();
            if(gankItems.size()!=Integer.valueOf(COUNT)){
                isLastLoad=true;
            }
            if(isFirstLoad){
                isFirstLoad =false;
            }else {
                if(isLastLoad){
                    Toast.makeText(getContext(),"没有更多啦", Toast.LENGTH_SHORT).show();
                }else {
                    //Toast.makeText(getContext(),"加载完成", Toast.LENGTH_SHORT).show();
                }

            }

            Log.d(TAG, "showGankItems: "+gankItems.size());
        }

        @Override
        public void getGankComplete() {
            swipeRefreshLayout.setRefreshing(false);

        }

        @Override
        public void getGankError() {
            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(getContext(),"加载失败", Toast.LENGTH_SHORT).show();
        }
    };
}
