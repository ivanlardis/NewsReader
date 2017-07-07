package com.lardis.ivan.newsreader.data.repository;

import com.lardis.ivan.newsreader.business.model.app.LTechModel;
import com.lardis.ivan.newsreader.business.model.converter.LTechConverter;
import com.lardis.ivan.newsreader.data.network.api.LTechApi;
import com.lardis.ivan.newsreader.utils.ResponseRep;
import com.lardis.ivan.newsreader.utils.State;

import android.util.Log;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;



public class LTechRepository implements ILTechRepository {

    LTechApi mApi;

    public LTechRepository(final LTechApi api) {
        mApi = api;
        newsBehaviorSubject = BehaviorSubject.create();
    }

    private static final String TAG = "LTechRepository";


    private BehaviorSubject<ResponseRep<List<LTechModel>>> newsBehaviorSubject;

    public Observable<ResponseRep<List<LTechModel>>> subscribeRep() {

        if (newsBehaviorSubject.getValue() == null) {
            loadData();
        }
        return newsBehaviorSubject.asObservable();
    }

    @Override
    public void loadData() {
        getItem()
                .timeout(10, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<LTechModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(final Throwable e) {
                        newsBehaviorSubject
                                .onNext(new ResponseRep<List<LTechModel>>(State.ERROR, null));
                        Log.d(TAG, "onError: " + e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(final List<LTechModel> newsModels) {
                        newsBehaviorSubject.onNext(new ResponseRep<List<LTechModel>>(State.SUCCES,newsModels));
                    }
                });


    }



    public Observable<List<LTechModel>> getItem() {
        return mApi.getLTech()
                .flatMapIterable(lTechModels -> lTechModels)
                .map(LTechConverter::mapItemLTech)
                .toSortedList();
    }


}
