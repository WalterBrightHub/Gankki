package com.example.ssmbu.gankki.service;


import com.example.ssmbu.gankki.service.entity.DailyGanks;
import com.example.ssmbu.gankki.service.entity.Ganks;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankService {
    //  http://gank.io/api/day/2015/08/06
    @GET("day/{yyyy}/{mm}/{dd}")
    Observable<DailyGanks> getGanksByDate(@Path("yyyy") String yyyy,
                                          @Path("mm") String mm,
                                          @Path("dd") String dd);
    //http://gank.io/api/data/Android/20/1
    @GET("data/{tag}/{count}/{page}")
    Observable<Ganks> getGankByTag(@Path("tag")String tag,
                                   @Path("count")String count,
                                   @Path("page")String page);
    //http://gank.io/api/random/data/Android/20
    @GET("random/data/{tag}/{count}")
    Observable<Ganks> getGankRandomByTag(@Path("tag")String tag,
                                         @Path("count")String count);

    //http://gank.io/api/search/query/listview/category/Android/count/10/page/1
    @GET("search/query/{keyword}/category/{tag}/count/{count}/page/{page}")
    Observable<Ganks> getGankByKeyword(@Path("keyword")String keyword,
                                       @Path("tag")String tag,
                                       @Path("count")String count,
                                       @Path("page")String page);
}
