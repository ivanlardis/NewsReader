package com.lardis.ivan.newsreader.repository;

import com.lardis.ivan.newsreader.data.network.NewsApi;
import com.lardis.ivan.newsreader.model.app.NewsRepModel;
import com.lardis.ivan.newsreader.model.app.NewsViewModel;
import com.lardis.ivan.newsreader.model.app.State;
import com.lardis.ivan.newsreader.model.converter.NewsConverter;
import com.lardis.ivan.newsreader.model.network.gazeta.RssGazeta;
import com.lardis.ivan.newsreader.model.network.lenta.RssLenta;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

/**
 * Created by black-sony on 25.05.17.
 */

public class NewsRepository implements INewsRepository {

    NewsApi mApi;

    public NewsRepository(final NewsApi api) {
        mApi = api;
        newsBehaviorSubject = BehaviorSubject.create();
    }

    private static final String TAG = "NewsRepository";


    private BehaviorSubject<NewsRepModel> newsBehaviorSubject;

    public Observable<NewsRepModel> subscribeRep() {

        if (newsBehaviorSubject.getValue() == null) {
            loadData();
        }
        return newsBehaviorSubject.asObservable();
    }

    @Override
    public void loadData() {
        getListAllItems()
                .timeout(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<NewsViewModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(final Throwable e) {
                        newsBehaviorSubject
                                .onNext(new NewsRepModel(State.ERROR, new ArrayList<NewsViewModel>()));
                        Log.d(TAG, "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(final List<NewsViewModel> newsModels) {
                        newsBehaviorSubject.onNext(new NewsRepModel(State.SUCCESS, newsModels));
                    }
                });


    }

    @NonNull
    public Observable<List<NewsViewModel>>  getListAllItems() {
        return Observable.merge(getItemGazeta(), getItemsLenta())
                .toSortedList();
    }

    public Observable<NewsViewModel> getItemGazeta() {
        return mApi.getItemGazeta()
                .flatMapIterable(RssGazeta::getItems)
                .map(NewsConverter::mapItemGazeta);
    }

    public Observable<NewsViewModel> getItemsLenta() {
        return mApi.getItemsLenta()
                .flatMapIterable(RssLenta::getItems)
                .map(NewsConverter::mapItemLenta);
    }
}
