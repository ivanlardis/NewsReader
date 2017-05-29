package com.lardis.ivan.newsreader.repository;

import com.lardis.ivan.newsreader.data.network.NewsApi;
import com.lardis.ivan.newsreader.model.network.gazeta.ItemGazeta;
import com.lardis.ivan.newsreader.model.network.gazeta.RssGazeta;
import com.lardis.ivan.newsreader.model.network.lenta.ItemLenta;
import com.lardis.ivan.newsreader.model.network.lenta.RssLenta;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by black-sony on 28.05.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsRepoTest {

    @Mock
    NewsApi mApi;

    NewsRepository newsRepository;

    int countGazeta = 128;

    int countLenta = 126;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        newsRepository = new NewsRepository(mApi);
        when(mApi.getItemGazeta())
                .thenReturn(Observable.just(getRssGazeta(countGazeta)));
        when(mApi.getItemsLenta())
                .thenReturn(Observable.just(getRssLenta(countLenta)));

    }


    @Test
    public void testGetItemGazeta() {
        newsRepository.getItemGazeta()
                .toList()
                .subscribe(newsViewModels -> assertEquals(newsViewModels.size(), countGazeta));

    }

    @Test
    public void testGetItemsLenta() {

        newsRepository.getItemsLenta()
                .toList()
                .subscribe(newsViewModels -> assertEquals(newsViewModels.size(), countLenta));

    }

    @Test
    public void testGetListAllItems() {
        newsRepository.getListAllItems()
                .subscribe(newsViewModels -> assertEquals(newsViewModels.size(), countLenta + countGazeta));

    }


    @NonNull
    private RssGazeta getRssGazeta(int count) {
        RssGazeta value = new RssGazeta();
        ArrayList<ItemGazeta> items = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            ItemGazeta itemGazeta = new ItemGazeta();
            itemGazeta.setAuthor("");
            itemGazeta.setDescription("");
            itemGazeta.setGuid("");
            itemGazeta.setLink("");
            itemGazeta.setPubDate("Sun, 28 May 2017 17:51:00 +0300");
            itemGazeta.setTitle("");
            items.add(itemGazeta);
        }

        value.setItems(items);
        return value;
    }

    @NonNull
    private RssLenta getRssLenta(int count) {
        RssLenta value = new RssLenta();
        ArrayList<ItemLenta> items = new ArrayList<>();
        for (int i = 0; i < count; i++) {

            ItemLenta itemLenta = new ItemLenta();

            itemLenta.setDescription("");
            itemLenta.setGuid("");
            itemLenta.setLink("");
            itemLenta.setPubDate("Sun, 28 May 2017 17:51:00 +0300");
            itemLenta.setTitle("");
            items.add(itemLenta);
        }

        value.setItems(items);
        return value;
    }

}
