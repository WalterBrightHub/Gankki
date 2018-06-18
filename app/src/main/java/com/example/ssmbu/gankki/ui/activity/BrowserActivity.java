package com.example.ssmbu.gankki.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.ssmbu.gankki.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowserActivity extends AppCompatActivity {

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

    private void initView() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if (url.equals("")) url = "http://gank.io/";
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        mWebview.getSettings().setJavaScriptEnabled(true);

        mWebview.loadUrl(url);
    }
}
