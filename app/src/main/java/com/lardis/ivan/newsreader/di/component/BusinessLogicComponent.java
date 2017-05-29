package com.lardis.ivan.newsreader.di.component;

import com.lardis.ivan.newsreader.di.model.BusinessLogicModule;
import com.lardis.ivan.newsreader.presentation.NewsPresenter;

import javax.inject.Singleton;

import dagger.Subcomponent;


@Singleton
@Subcomponent(modules = {BusinessLogicModule.class})
public interface BusinessLogicComponent {

    void inject(NewsPresenter entry);


}
