package com.example.ssmbu.gankki.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.service.entity.GankItem;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowserActivity extends AppCompatActivity {
    private static final String TAG = "BrowserActivity";
    private Intent mIntent;
    @BindView(R.id.share)
    FloatingActionButton share;
    @BindView(R.id.star)
    FloatingActionButton mStar;
    @BindView(R.id.floatingActionsMenu)
    FloatingActionsMenu floatingActionsMenu;
    //private String mUrl;
    //private String mDesc;
    //private String mId;

    private GankItem mGankItem;

    private boolean starState;

    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);

        initView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browse_menu, menu);
        return true;
    }

    private void initView() {
        mIntent = getIntent();
        //mUrl = mIntent.getStringExtra("url");
        //mDesc=mIntent.getStringExtra("desc");
        //mId=mIntent.getStringExtra("_id");
        mGankItem=(GankItem) mIntent.getParcelableExtra("gankItem_data");
        if (mGankItem.getUrl().equals("")) {
            mGankItem.setUrl("http://gank.io/");
        }
        if(mGankItem.getDesc().equals("")){
            //mDesc = "快来看这个干货！";
            mGankItem.setDesc("快来看这个干货");
        }
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                progressBar.setVisibility(View.GONE);
                floatingActionsMenu.setVisibility(View.VISIBLE);
            }
        });
        mWebview.getSettings().setJavaScriptEnabled(true);

        mWebview.loadUrl(mGankItem.getUrl());

        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mStar.setIcon(R.drawable.ic_star_white_24dp);

                floatingActionsMenu.toggle();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.toggle();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, mGankItem.getDesc()+"\n"+ mGankItem.getUrl());
                startActivity(Intent.createChooser(intent, "分享至……"));

            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private void toggleStar(){

    }
}
