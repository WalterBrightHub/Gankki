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

        initData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private LayoutInflater Inflater;
    private List<String> titles;
    private List<Fragment> fragments;


    private void initData(){
        titles=new ArrayList<>();
        fragments=new ArrayList<>();

        titles.add("干货");
        titles.add("iOS");
        titles.add("Android");
        titles.add("前端");
        titles.add("瞎推荐");
        titles.add("拓展资源");
        titles.add("福利");
        titles.add("休息视频");

        for(int i=0;i<titles.size();i++){
            GankListFragment fragment=new GankListFragment();
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
        //tabLayout.setTabsFromPagerAdapter(mAdapter);
    }
}































