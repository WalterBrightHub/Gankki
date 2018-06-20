package com.example.ssmbu.gankki.service.model;

import android.util.Log;

import com.example.ssmbu.gankki.service.GankService;
import com.example.ssmbu.gankki.service.entity.DailyGanks;
import com.example.ssmbu.gankki.service.entity.GankItem;
import com.example.ssmbu.gankki.service.entity.GankItem_Table;
import com.example.ssmbu.gankki.service.entity.Ganks;
import com.google.gson.GsonBuilder;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GankModel {
    private static final String TAG = "GankModel";
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

    public Observable<Ganks> getGanksByKeyword(String keyword,String tag,String count,String page){
        return service.getGankByKeyword(keyword, tag, count, page);
    }

    public Observable<List<GankItem>> getCollectionsByTag(final String tag){
        return Observable.create(new ObservableOnSubscribe<List<GankItem>>() {
            @Override
            public void subscribe(ObservableEmitter<List<GankItem>> emitter) throws Exception {
                List<GankItem> gankItems;
                //Log.d(TAG, "subscribe: "+tag+" "+count+" "+page);
                try{
                    if(tag.equals("all")){
                        gankItems= SQLite.select()
                                .from(GankItem.class)
                                .queryList();
                        emitter.onNext(gankItems);
                    }
                    else {
                        gankItems=SQLite.select()
                                .from(GankItem.class)
                                .where(GankItem_Table.type.eq(tag))
                                .queryList();
                        emitter.onNext(gankItems);
                    }
                    Log.d(TAG, "subscribe: 请求到的数据量"+gankItems.size());
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        });
    }
}
