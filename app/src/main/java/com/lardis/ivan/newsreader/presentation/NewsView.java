package com.lardis.ivan.newsreader.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lardis.ivan.newsreader.model.app.NewsViewModel;

import java.util.List;

/**
 * Created by black-sony on 25.05.17.
 */

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface NewsView extends MvpView {

    void showData(List<NewsViewModel> newsViewModels);

    void showWarning(boolean show);

    void showProgress(boolean show);
}
