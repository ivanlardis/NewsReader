package com.lardis.ivan.newsreader.di.component;

import com.lardis.ivan.newsreader.data.network.api.LTechApi;
import com.lardis.ivan.newsreader.data.repository.LTechRepository;
import com.lardis.ivan.newsreader.di.model.BusinessLogicModule;
import com.lardis.ivan.newsreader.di.model.ModelModule;

import dagger.Component;


@Component(modules = {ModelModule.class})
public interface AppComponent {

    BusinessLogicComponent plus(BusinessLogicModule businessLogicModule);

    void inject(LTechRepository currencyRepository);

    LTechApi getNewsApi();

}
