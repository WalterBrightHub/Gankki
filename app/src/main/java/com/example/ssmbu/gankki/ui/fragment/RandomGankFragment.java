package com.example.ssmbu.gankki.ui.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ssmbu.gankki.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RandomGankFragment extends Fragment {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_browse_gank, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private List<String> mTitles;
    private List<String> mTags;
    private List<Fragment> mFragments;


    private void initData(){
        mTitles =new ArrayList<>();
        mFragments =new ArrayList<>();
        mTags =new ArrayList<>();

        mTitles.add("真·全栈");
        mTitles.add("Android");
        mTitles.add("iOS");
        mTitles.add("App");
        mTitles.add("前端");
        mTitles.add("瞎推荐");
        mTitles.add("拓展资源");
        mTitles.add("休息视频");
        mTitles.add("福利");

        mTags.add("all");
        mTags.add("Android");
        mTags.add("iOS");
        mTags.add("App");
        mTags.add("前端");
        mTags.add("瞎推荐");
        mTags.add("拓展资源");
        mTags.add("休息视频");
        mTags.add("福利");

        for(int i = 0; i< mTitles.size(); i++){
            RandomGankRVFragment fragment=RandomGankRVFragment.newInstance(mTags.get(i));
            //BrowseGankRVFragment fragment= BrowseGankRVFragment.newInstance(mTags.get(i));
            mFragments.add(fragment);

            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
        }

        FragmentPagerAdapter mAdapter=new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                //return 0;
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        };

        mViewpager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
        //预加载所有页面，否则来回切换页面（fragment）的时候会重新创建页面
        //随机数据就不需要预加载啦
        //mViewpager.setOffscreenPageLimit(mTitles.size());
        //mTabLayout.setTabsFromPagerAdapter(mAdapter);

    }
}
