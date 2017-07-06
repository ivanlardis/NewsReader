package com.lardis.ivan.newsreader.presentation.news;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lardis.ivan.newsreader.business.model.app.LTechModel;

import java.util.List;



@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface LTechNewsView extends MvpView {

    void showData(List<LTechModel> newsViewModels);

    void showWarning(boolean show);

    void showProgress(boolean show);
}
