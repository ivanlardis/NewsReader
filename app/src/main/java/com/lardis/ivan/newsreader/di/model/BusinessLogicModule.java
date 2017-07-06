package com.lardis.ivan.newsreader.di.model;


import com.lardis.ivan.newsreader.data.network.api.LTechApi;
import com.lardis.ivan.newsreader.data.repository.ILTechRepository;
import com.lardis.ivan.newsreader.data.repository.LTechRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class BusinessLogicModule {

    @Provides
    @Singleton
    public ILTechRepository provideLTechRepository(LTechApi newsApi) {
        return new LTechRepository(newsApi);
    }


}
