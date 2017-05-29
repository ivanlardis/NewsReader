package com.lardis.ivan.newsreader.di.model;

import com.lardis.ivan.newsreader.data.network.ApiFactoty;
import com.lardis.ivan.newsreader.data.network.NewsApi;
import com.lardis.ivan.newsreader.data.network.OkHttpClientFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


@Module
public class ModelModule {

    public ModelModule() {
    }

    public static     String BASE_LENTA_URL = "https://lenta.ru";
     public static final   String BASE_GAZETA_URL = "https://www.gazeta.ru";

    @Provides
    public NewsApi provideNewsApi(
            Converter.Factory converterFactory,
            CallAdapter.Factory callAdapterFactory,
            OkHttpClient okHttpClient) {
        ApiFactoty<NewsApi> apiFactoty =
                new ApiFactoty(BASE_LENTA_URL,
                        converterFactory,
                        callAdapterFactory,
                        okHttpClient);
        return apiFactoty.create(NewsApi.class);
    }

    @Provides
    public Converter.Factory provideConverterFactory() {
        return SimpleXmlConverterFactory.create();
    }

    @Provides
    public CallAdapter.Factory provideCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    public OkHttpClient provideOkHttpClient() {
        return (new OkHttpClientFactory()).create();
    }


}
