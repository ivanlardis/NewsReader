package com.lardis.ivan.newsreader.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lardis.ivan.newsreader.di.DI;
import com.lardis.ivan.newsreader.model.app.NewsRepModel;
import com.lardis.ivan.newsreader.repository.INewsRepository;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by black-sony on 25.05.17.
 */

@InjectViewState
public class NewsPresenter extends MvpPresenter<NewsView> {

    @Inject
    INewsRepository repository;

    public NewsPresenter() {
        DI.getInstance().componentManager().businessLogicComponent().inject(this);
        repository.subscribeRep()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showData);
    }

    @Override
    public void attachView(final NewsView view) {
        super.attachView(view);
    }

    private void showData(final NewsRepModel newsRepModel) {
        getViewState().showProgress(false);
        switch (newsRepModel.getState()) {
            case ERROR:
                getViewState().showWarning(true);
                break;

            case SUCCESS:
                getViewState().showData(newsRepModel.getNewsViewModels());
                getViewState().showWarning(false);
                break;
        }
    }

    public void loadData() {
        getViewState().showProgress(true);
        repository.loadData();

    }

}
