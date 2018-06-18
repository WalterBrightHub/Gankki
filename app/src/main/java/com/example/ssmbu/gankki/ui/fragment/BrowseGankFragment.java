package com.example.ssmbu.gankki.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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

public class BrowseGankFragment extends Fragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

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

    private LayoutInflater Inflater;
    private List<String> titles;
    private List<String> tags;
    private List<Fragment> fragments;


    private void initData(){
        titles=new ArrayList<>();
        fragments=new ArrayList<>();
        tags=new ArrayList<>();

        titles.add("真·全栈");
        titles.add("iOS");
        titles.add("Android");
        titles.add("前端");
        titles.add("瞎推荐");
        titles.add("拓展资源");
        titles.add("福利");
        titles.add("休息视频");

        tags.add("all");
        tags.add("iOS");
        tags.add("Android");
        tags.add("前端");
        tags.add("瞎推荐");
        tags.add("拓展资源");
        tags.add("福利");
        tags.add("休息视频");

        for(int i=0;i<titles.size();i++){
            GankListFragment fragment=GankListFragment.newInstance(tags.get(i));
            fragments.add(fragment);

            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }

        FragmentPagerAdapter mAdapter=new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                //return 0;
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };

        viewpager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewpager);
        //预加载所有页面，否则来回切换页面（fragment）的时候会重新创建页面
        viewpager.setOffscreenPageLimit(titles.size());
        //tabLayout.setTabsFromPagerAdapter(mAdapter);

    }
}































