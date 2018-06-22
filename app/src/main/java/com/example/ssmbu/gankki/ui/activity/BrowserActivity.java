package com.example.ssmbu.gankki.ui.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ssmbu.gankki.R;
import com.example.ssmbu.gankki.service.entity.GankItem;
import com.example.ssmbu.gankki.service.entity.GankItem_Table;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowserActivity extends AppCompatActivity {
    private static final String TAG = "BrowserActivity";
    @BindView(R.id.floatingback)
    ImageView floatingback;
    private Intent mIntent;
    @BindView(R.id.share)
    FloatingActionButton share;
    @BindView(R.id.star)
    FloatingActionButton mStar;
    @BindView(R.id.floatingActionsMenu)
    FloatingActionsMenu floatingActionsMenu;

    @BindView(R.id.webview)
    WebView mWebview;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private GankItem mGankItem;

    private boolean mStarState = false;
    private boolean mStarStateOnCreate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        ButterKnife.bind(this);

        mIntent = getIntent();
        mGankItem = (GankItem) mIntent.getParcelableExtra("gankItem_data");
        if (mGankItem.getUrl().equals("")) {
            mGankItem.setUrl("http://gank.io/");
        }
        if (mGankItem.getDesc().equals("")) {
            mGankItem.setDesc("快来看这个干货");
        }
        initStarState();
        initView();


    }


    private void initStarState() {
        List<GankItem> gankItems =
                SQLite.select().from(GankItem.class).where(GankItem_Table._id.eq(mGankItem.get_id())).queryList();
        if (gankItems.size() == 0) {
            //Log.d(TAG, "initStarState: 未收藏");
            mStarState = false;
            mStar.setIcon(R.drawable.ic_star_border_white_24dp);
        } else {
            //Log.d(TAG, "initStarState: 已收藏");
            mStarState = true;
            mStar.setIcon(R.drawable.ic_star_white_24dp);
        }
        mStarStateOnCreate = mStarState;
    }

    private void initView() {
        mWebview.setWebViewClient(new WebViewClient() {

            //https://blog.csdn.net/qq_36113598/article/details/78175478
            //启动知乎，bilibili等app，防止报错UNKNOWN SCHEME
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) return false;
                Log.d(TAG, "shouldOverrideUrlLoading: " + url);
                try {
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        view.loadUrl(url);
                        return true;
                    } else {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return false;
                }
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                Log.d(TAG, "onPageCommitVisible: 我准备好了！");
                super.onPageCommitVisible(view, url);
                progressBar.setVisibility(View.INVISIBLE);
                floatingActionsMenu.setVisibility(View.VISIBLE);
            }

            //5.0似乎不支持onPageCommitVisibleg
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
                floatingActionsMenu.setVisibility(View.VISIBLE);
            }
        });
        mWebview.getSettings().setJavaScriptEnabled(true);

        //Log.d(TAG, "shouldOverrideUrlLoading: "+mGankItem.getUrl());
        mWebview.loadUrl(mGankItem.getUrl());

        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleStarState();
                //在这里停顿，让用户看到状态
                floatingActionsMenu.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                            floatingActionsMenu.collapse();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, mGankItem.getDesc() + "\n" + mGankItem.getUrl());
                startActivity(Intent.createChooser(intent, "分享至……"));

            }
        });
        //展开FAB显示背景空白，点击空白收起FAB
        floatingback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.toggle();
            }
        });

        floatingActionsMenu.setOnFloatingActionsMenuUpdateListener(new FloatingActionsMenu.OnFloatingActionsMenuUpdateListener() {
            @Override
            public void onMenuExpanded() {
                floatingback.animate()
                        .setDuration(200)
                        .alpha(0.7f)
                        .withStartAction(new Runnable() {
                            @Override
                            public void run() {
                                floatingback.setVisibility(View.VISIBLE);
                            }
                        })
                        .start();

            }

            @Override
            public void onMenuCollapsed() {
                floatingback.animate()
                        .setDuration(200)
                        .alpha(0f)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                floatingback.setVisibility(View.INVISIBLE);
                            }
                        })
                        .start()
                ;

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

    @Override
    protected void onDestroy() {
        if (mStarStateOnCreate && !mStarState) {
            //取消收藏
            SQLite.delete()
                    .from(GankItem.class)
                    .where(GankItem_Table._id.eq(mGankItem.get_id()))
                    .query();
        } else if (!mStarStateOnCreate && mStarState) {
            boolean save = mGankItem.save();
            if (!save) {
                Toast.makeText(BrowserActivity.this, "收藏失败啦", Toast.LENGTH_SHORT).show();
            }

        }
        //Log.d(TAG, "onDestroy: "+getDBCounts());
        super.onDestroy();
    }

    private void toggleStarState() {
        if (mStarState) {
            mStarState = false;
            mStar.setTitle("收藏");
            mStar.setIcon(R.drawable.ic_star_border_white_24dp);
        } else {
            mStarState = true;
            mStar.setTitle("已收藏");
            mStar.setIcon(R.drawable.ic_star_white_24dp);
        }
    }

    private int getDBCounts() {
        return SQLite.select().from(GankItem.class).queryList().size();
    }
}
