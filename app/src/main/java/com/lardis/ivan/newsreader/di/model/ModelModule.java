package com.lardis.ivan.newsreader.di.model;

import com.lardis.ivan.newsreader.data.network.api.ApiFactoty;
import com.lardis.ivan.newsreader.data.network.api.LTechApi;
import com.lardis.ivan.newsreader.data.network.api.OkHttpClientFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class ModelModule {

    public ModelModule() {
    }

    public static String BASE_URL = "http://dev-exam.l-tech.ru";

    @Provides
    public LTechApi provideNewsApi(
            Converter.Factory converterFactory,
            CallAdapter.Factory callAdapterFactory,
            OkHttpClient okHttpClient) {
        ApiFactoty<LTechApi> apiFactoty =
                new ApiFactoty(BASE_URL,
                        converterFactory,
                        callAdapterFactory,
                        okHttpClient);
        return apiFactoty.create(LTechApi.class);
    }

    @Provides
    public Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
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
