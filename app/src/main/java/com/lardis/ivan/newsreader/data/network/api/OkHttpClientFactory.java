package com.lardis.ivan.newsreader.data.network.api;

import okhttp3.OkHttpClient;


public class OkHttpClientFactory {

    public OkHttpClient create() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        return okHttpBuilder.build();
    }
}
