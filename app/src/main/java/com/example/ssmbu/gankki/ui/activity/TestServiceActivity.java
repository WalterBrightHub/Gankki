package com.example.ssmbu.gankki.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.service.entity.GankItem;
import com.example.ssmbu.gankki.service.presenter.GankPresenter;
import com.example.ssmbu.gankki.service.view.GankView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestServiceActivity extends AppCompatActivity {

    private GankPresenter gankPresenter;

    @BindView(R.id.btn_getGank)
    Button btnGetGank;
    @BindView(R.id.btn_searchGank)
    Button btnSearchGank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);
        ButterKnife.bind(this);

        gankPresenter=new GankPresenter(gankView);
    }

    private GankView gankView=new GankView() {
        @Override
        public void showGankItems(List<GankItem> gankItems) {

        }

        @Override
        public void getGankComplete() {
            Toast.makeText(TestServiceActivity.this,"getGankComplete",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void getGankError() {
            Toast.makeText(TestServiceActivity.this,"getGankError",Toast.LENGTH_SHORT).show();
        }
    };

    @OnClick({R.id.btn_getGank, R.id.btn_searchGank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getGank:
                gankPresenter.getGanksByDate("2018","06","15");
                break;
            case R.id.btn_searchGank:
                break;
        }
    }
}
