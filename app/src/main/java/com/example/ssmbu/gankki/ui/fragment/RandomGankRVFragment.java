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

public class RandomGankRVFragment extends Fragment {
    private static final String TAG = "BrowseGankRVFragment";
    //一次加载20条数据
    private static final String COUNT = "20";




    private String mTag;
    private List<GankItem> mGankItems =new ArrayList<>();
    private BrowseGankAdapter mBrowseGankAdapter;


    public static RandomGankRVFragment newInstance(String tag){
        final RandomGankRVFragment randomGankRVFragment =new RandomGankRVFragment();
        final Bundle args=new Bundle();
        args.putString("tag",tag);
        randomGankRVFragment.setArguments(args);
        return randomGankRVFragment;
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_browse_gank_rv, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mGankPresenter=new GankPresenter(mGankView);

        //mGankPresenter.getGanksByTag(mTag,COUNT,String.valueOf(mPage++));
        mGankPresenter.getGanksRandomByTag(mTag,COUNT);
        swipeRefreshLayout.setRefreshing(true);
    }

    private void initView(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mGankItems.clear();
                //会闪烁，体验不好
                //mBrowseGankAdapter.notifyDataSetChanged();
                mGankPresenter.getGanksRandomByTag(mTag,COUNT);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBrowseGankAdapter =new BrowseGankAdapter(mGankItems,RandomGankRVFragment.this);
        recyclerView.setAdapter(mBrowseGankAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    if(!recyclerView.canScrollVertically(1)){
                        mGankPresenter.getGanksRandomByTag(mTag,COUNT);
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
