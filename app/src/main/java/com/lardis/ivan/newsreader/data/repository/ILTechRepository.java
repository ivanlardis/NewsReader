package com.lardis.ivan.newsreader.data.repository;

import com.lardis.ivan.newsreader.business.model.app.LTechModel;
import com.lardis.ivan.newsreader.utils.ResponseRep;

import java.util.List;

import rx.Observable;



public interface ILTechRepository {

    void loadData();

      Observable<ResponseRep<List<LTechModel>>> subscribeRep();
}
