package com.lardis.ivan.newsreader.presentation.news;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lardis.ivan.newsreader.business.model.app.LTechModel;
import com.lardis.ivan.newsreader.data.repository.ILTechRepository;
import com.lardis.ivan.newsreader.di.DI;
import com.lardis.ivan.newsreader.utils.ResponseRep;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


@InjectViewState
public class LTechNewsPresenter extends MvpPresenter<LTechNewsView> {

    @Inject
    ILTechRepository repository;

    public LTechNewsPresenter() {
        DI.getInstance().componentManager().businessLogicComponent().inject(this);
        repository.subscribeRep()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showData);
    }

    @Override
    public void attachView(final LTechNewsView view) {
        super.attachView(view);
    }

    private void showData(final ResponseRep<List<LTechModel>> newsRepModel) {
        getViewState().showProgress(false);
        switch (newsRepModel.getState()) {
            case ERROR:
                getViewState().showWarning(true);
                break;

            case SUCCES:
                getViewState().showData(newsRepModel.getData());
                getViewState().showWarning(false);
                break;
        }
    }

    public void loadData() {
        getViewState().showProgress(true);
        repository.loadData();

    }

}
