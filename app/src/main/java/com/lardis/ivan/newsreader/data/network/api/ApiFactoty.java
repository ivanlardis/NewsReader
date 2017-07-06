package com.lardis.ivan.newsreader.data.network.api;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;


public class ApiFactoty<T> {

    private Retrofit retrofit;

    public ApiFactoty(String baseUrl,
            Converter.Factory converterFactory,
            CallAdapter.Factory callAdapterFactory,
            OkHttpClient okHttpClient) {
        retrofit = (new Retrofit.Builder())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(callAdapterFactory)
                .addConverterFactory(converterFactory)
                .build();
    }

    public T create(Class<T> javaClass) {
        return retrofit.create(javaClass);
    }

}
