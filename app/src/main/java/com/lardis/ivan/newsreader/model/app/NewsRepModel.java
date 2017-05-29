package com.lardis.ivan.newsreader.model.app;

import java.util.List;

/**
 * Created by black-sony on 27.05.17.
 */

public class NewsRepModel {

    private State mState;

    private List<NewsViewModel> mNewsViewModels;

    public NewsRepModel(final State state,
            final List<NewsViewModel> newsViewModels) {
        mState = state;
        mNewsViewModels = newsViewModels;
    }

    public State getState() {
        return mState;
    }

    public void setState(final State state) {
        mState = state;
    }

    public List<NewsViewModel> getNewsViewModels() {
        return mNewsViewModels;
    }

    public void setNewsViewModels(final List<NewsViewModel> newsViewModels) {
        mNewsViewModels = newsViewModels;
    }
}
