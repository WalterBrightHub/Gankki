package com.example.ssmbu.gankki.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchGankFragment extends Fragment {
    private static final String TAG = "SearchGankFragment";
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    Unbinder unbinder;
    @BindView(R.id.keyword)
    EditText mKeyword;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search_gank, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEARCH){
                    MyUtils.hideSoftKeyboard(getContext(), mKeyword);
                    loadData();
                    Log.d(TAG, "onEditorAction: 加载数据 "+mKeyword.getText().toString());
                    //Toast.makeText(getContext(),"搜索！",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private List<String> mTitles;
    private List<String> mTags;
    private List<Fragment> mFragments;


    private void loadData() {
        if(mFragments!=null){
            mFragments.removeAll(mFragments);
        }

        mTitles = new ArrayList<>();
        mFragments = new ArrayList<>();
        mTags = new ArrayList<>();

        mTitles.add("真·全栈");
        mTitles.add("Android");
        mTitles.add("iOS");
        mTitles.add("App");
        mTitles.add("前端");
        mTitles.add("瞎推荐");
        mTitles.add("拓展资源");
        mTitles.add("休息视频");
        //mTitles.add("福利");

        mTags.add("all");
        mTags.add("Android");
        mTags.add("iOS");
        mTags.add("App");
        mTags.add("前端");
        mTags.add("瞎推荐");
        mTags.add("拓展资源");
        mTags.add("休息视频");
        //mTags.add("福利");

        mFragments.clear();
        mTabLayout.removeAllTabs();
        for (int i = 0; i < mTitles.size(); i++) {
            //关键词要嵌入到URL中，不管怎么样先加个空格
            String keyword=" "+mKeyword.getText().toString();
            SearchGankRVFragment fragment = SearchGankRVFragment.newInstance(keyword,mTags.get(i));
            mFragments.add(fragment);
            if(mViewpager.getAdapter()!=null){
                mViewpager.getAdapter().notifyDataSetChanged();
            }

            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
        }

        FragmentStatePagerAdapter adapter1=new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }
        };

        mViewpager.setAdapter(adapter1);
        mTabLayout.setupWithViewPager(mViewpager);
        //预加载所有页面，否则来回切换页面（fragment）的时候会重新创建页面
        //搜索也不预加载了吧
        //mViewpager.setOffscreenPageLimit(mTitles.size());
        //mTabLayout.setTabsFromPagerAdapter(mAdapter);

    }
}
