package com.lardis.ivan.newsreader.di.model;


import com.lardis.ivan.newsreader.data.network.NewsApi;
import com.lardis.ivan.newsreader.repository.INewsRepository;
import com.lardis.ivan.newsreader.repository.NewsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class BusinessLogicModule {

    @Provides
    @Singleton
    public INewsRepository provideLangRepository(NewsApi newsApi) {
        return new NewsRepository(newsApi);
    }


}
