package com.lardis.ivan.newsreader.di;


import com.lardis.ivan.newsreader.di.component.ComponentManager;


public class DI {

    private static DI ourInstance;

    private ComponentManager componentManager;


    public static void init() {
        ourInstance = new DI();
    }


    public static DI getInstance() {
        return ourInstance;
    }

    private DI() {

        componentManager = new ComponentManager();

    }


    public ComponentManager componentManager() {
        return componentManager;
    }

}
