package com.lardis.ivan.newsreader.di.component;


import com.lardis.ivan.newsreader.di.model.BusinessLogicModule;
import com.lardis.ivan.newsreader.di.model.ModelModule;
import com.lardis.ivan.newsreader.di.model.NavigationModule;


public class ComponentManager {

    AppComponent internalAppComponent;

    BusinessLogicComponent internalBusinessLogicComponent;

    NavigationComponent navigationComponent;

    public ComponentManager() {
        internalAppComponent = DaggerAppComponent
                .builder()
                .modelModule(new ModelModule())
                .build();

        internalBusinessLogicComponent = internalAppComponent.plus(new BusinessLogicModule());

        navigationComponent = DaggerNavigationComponent
                .builder()
                .navigationModule(new NavigationModule())
                .build();
    }

    public BusinessLogicComponent businessLogicComponent() {
        return internalBusinessLogicComponent;

    }

    public AppComponent appComponent() {
        return internalAppComponent;

    }

    public NavigationComponent navigationComponent() {
        return navigationComponent;

    }
}
