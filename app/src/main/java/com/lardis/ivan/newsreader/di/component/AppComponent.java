package com.lardis.ivan.newsreader.di.component;

import com.lardis.ivan.newsreader.data.network.NewsApi;
import com.lardis.ivan.newsreader.di.model.BusinessLogicModule;
import com.lardis.ivan.newsreader.di.model.ModelModule;
import com.lardis.ivan.newsreader.repository.NewsRepository;

import dagger.Component;


@Component(modules = {ModelModule.class})
public interface AppComponent {

    BusinessLogicComponent plus(BusinessLogicModule businessLogicModule);

    void inject(NewsRepository currencyRepository);

    NewsApi getNewsApi();

}
