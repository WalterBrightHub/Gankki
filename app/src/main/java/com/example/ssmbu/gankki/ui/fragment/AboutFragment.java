package com.example.ssmbu.gankki.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ssmbu.gankki.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AboutFragment extends Fragment {

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_about, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cardView, R.id.gankkiOnGithub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardView:
                break;
            case R.id.gankkiOnGithub:
                Intent intent =new Intent();
                intent.setData(Uri.parse("https://github.com/WalterBrightHub/Gankki"));
                intent.setAction("android.intent.action.VIEW");
                startActivity(intent);
                break;
        }
    }
}
