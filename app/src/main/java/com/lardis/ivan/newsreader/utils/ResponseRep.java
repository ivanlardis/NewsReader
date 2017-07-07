package com.lardis.ivan.newsreader.utils;

import android.support.annotation.Nullable;

public class ResponseRep<T> {

    private State mState;

    @Nullable
    private T data;


    public ResponseRep(final State state, final T data) {
        mState = state;
        this.data = data;
    }

    public State getState() {
        return mState;
    }

    public void setState(final State state) {
        mState = state;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

}
