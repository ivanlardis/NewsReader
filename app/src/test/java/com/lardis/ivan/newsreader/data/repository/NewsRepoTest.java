package com.lardis.ivan.newsreader.data.repository;

import com.lardis.ivan.newsreader.data.network.api.LTechApi;
import com.lardis.ivan.newsreader.data.network.model.LTechModelNW;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NewsRepoTest {

    @Mock
    LTechApi mApi;

    LTechRepository newsRepository;

    int count = 128;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        newsRepository = new LTechRepository(mApi);
        when(mApi.getLTech())
                .thenReturn(Observable.just(getTestData(count)));


    }


    @Test
    public void test () {
        newsRepository.getItem()
                           .subscribe(newsViewModels -> assertEquals(newsViewModels.size(), count));

    }




    @NonNull
    private List<LTechModelNW> getTestData(int count) {

        ArrayList<LTechModelNW> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            LTechModelNW e = new LTechModelNW();

            e.setDate("2016-01-26 09:39:54");
            e.setId(1);
            e.setImage("");
            e.setSort(1);
            e.setText("");
            e.setTitle("");


            items.add(e);
        }

        return items;
    }



}
