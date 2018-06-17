package com.example.ssmbu.gankki.service;


import com.example.ssmbu.gankki.service.entity.DailyGank;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GankService {
    //  http://gank.io/api/day/2015/08/06
    @GET("day/{yyyy}/{mm}/{dd}")
    Observable<DailyGank> getDailyGank(@Path("yyyy") String yyyy,
                                       @Path("mm") String mm,
                                       @Path("dd") String dd);
}
