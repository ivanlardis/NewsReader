package com.lardis.ivan.newsreader.data.network;


import com.lardis.ivan.newsreader.model.network.gazeta.RssGazeta;
import com.lardis.ivan.newsreader.model.network.lenta.RssLenta;

import retrofit2.http.GET;
import rx.Observable;

public interface NewsApi {


    @GET("rss")
    Observable<RssLenta> getItemsLenta();

    @GET("https://www.gazeta.ru/export/rss/lenta.xml")
    Observable<RssGazeta> getItemGazeta();

}
