package com.example.ssmbu.gankki.service.presenter;

import android.util.Log;

import com.example.ssmbu.gankki.service.entity.DailyGank;
import com.example.ssmbu.gankki.service.model.GankModel;
import com.example.ssmbu.gankki.service.view.GankView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GankPresenter{
    private static final String TAG = "GankPresenter";
    private GankModel gankModel;
    private GankView gankView;
    private DailyGank mDailyGank;
    public GankPresenter(GankView gankView){
        this.gankView=gankView;
        gankModel=new GankModel();
    }



    public void getGanks(String yyyy,String mm,String dd){
        gankModel.getDailyGank(yyyy, mm, dd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyGank>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyGank dailyGank) {
                        mDailyGank = dailyGank;
                        Log.d(TAG, "onNext: "+ dailyGank.getResults().get休息视频().get(0).getDesc());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: get gank error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: get gank complete");
                    }

                    });
    }
}
