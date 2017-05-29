package com.lardis.ivan.newsreader.repository;

import com.lardis.ivan.newsreader.model.app.NewsRepModel;

import rx.Observable;

/**
 * Created by black-sony on 25.05.17.
 */

public interface INewsRepository {

    void loadData();

    Observable<NewsRepModel> subscribeRep();
}
