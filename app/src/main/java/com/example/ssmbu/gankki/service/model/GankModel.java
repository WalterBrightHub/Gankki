package com.example.ssmbu.gankki.service.model;

import com.example.ssmbu.gankki.service.GankService;
import com.example.ssmbu.gankki.service.entity.DailyGanks;
import com.example.ssmbu.gankki.service.entity.Ganks;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankModel {
    private Retrofit retrofit;
    private GankService service;
    private GsonConverterFactory factory=GsonConverterFactory.create(new GsonBuilder().create());
    public GankModel(){
        OkHttpClient client=new OkHttpClient();
        retrofit=new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service=retrofit.create(GankService.class);
    }
    public Observable<DailyGanks> getGanksByDate(String yyyy, String mm, String dd){
        return service.getGanksByDate(yyyy, mm, dd);
    }

    public Observable<Ganks> getGanksByTag(String tag,String count,String page){
        return service.getGankByTag(tag, count, page);
    }

    public Observable<Ganks> getGanksRandomByTag(String tag,String count){
        return service.getGankRandomByTag(tag, count);
    }
}
