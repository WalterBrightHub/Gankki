package com.example.ssmbu.gankki.service.presenter;

import android.util.Log;

import com.example.ssmbu.gankki.service.entity.DailyGanks;
import com.example.ssmbu.gankki.service.entity.GankItem;
import com.example.ssmbu.gankki.service.entity.Ganks;
import com.example.ssmbu.gankki.service.model.GankModel;
import com.example.ssmbu.gankki.service.view.GankView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class GankPresenter{
    private static final String TAG = "GankPresenter";
    private GankModel gankModel;
    private GankView gankView;
    private DailyGanks mDailyGanks;
    public GankPresenter(GankView gankView){
        this.gankView=gankView;
        gankModel=new GankModel();
    }



    public void getGanksByDate(String yyyy, String mm, String dd){
        gankModel.getGanksByDate(yyyy, mm, dd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DailyGanks>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DailyGanks dailyGanks) {
                        mDailyGanks = dailyGanks;
                        //Log.d(TAG, "onNext: "+ dailyGanks.getResults().get休息视频().get(0).getDesc());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: get daily gank error");
                        gankView.getGankError();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: get daily gank complete");
                        gankView.getGankComplete();
                    }
                });
    }

    List<GankItem> gankItems;
    public void getGanksByTag(String tag,String count,String page){
        gankModel.getGanksByTag(tag, count, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Ganks>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Ganks ganks) {
                            gankItems=ganks.getGankItems();
                    }

                    @Override
                    public void onError(Throwable e) {
                        gankView.getGankError();
                    }

                    @Override
                    public void onComplete() {
                        gankView.getGankComplete();
                        gankView.showGankItems(gankItems);
                        Log.d(TAG, "onComplete: show "+gankItems.size());
                    }
                });
    }

    public void getGanksRandomByTag(String tag,String count){
        gankModel.getGanksRandomByTag(tag, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Ganks>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Ganks ganks) {
                        gankItems=ganks.getGankItems();
                    }

                    @Override
                    public void onError(Throwable e) {
                        gankView.getGankError();
                    }

                    @Override
                    public void onComplete() {
                        gankView.getGankComplete();
                        gankView.showGankItems(gankItems);
                        Log.d(TAG, "onComplete: show "+gankItems.size());
                    }
                });
    }
}
