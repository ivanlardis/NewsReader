package com.lardis.ivan.newsreader.data.network.api;


import com.lardis.ivan.newsreader.data.network.model.LTechModelNW;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface LTechApi {


    @GET("/")
    Observable<List<LTechModelNW>> getLTech();


}
