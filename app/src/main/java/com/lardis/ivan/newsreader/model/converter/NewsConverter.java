package com.lardis.ivan.newsreader.model.converter;

import com.lardis.ivan.newsreader.model.app.NewsViewModel;
import com.lardis.ivan.newsreader.model.network.gazeta.ItemGazeta;
import com.lardis.ivan.newsreader.model.network.lenta.Enclosure;
import com.lardis.ivan.newsreader.model.network.lenta.ItemLenta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by black-sony on 27.05.17.
 */

public class NewsConverter {

    private static final String TAG = "NewsConverter";

    private static DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z",
            Locale.ENGLISH);

    public static NewsViewModel mapItemGazeta(ItemGazeta itemGazeta) {
        return new NewsViewModel(
                "lenta.ru",
                itemGazeta.getTitle().trim(),
                itemGazeta.getDescription().trim(),
                getDateOrDie(itemGazeta.getPubDate()),
                itemGazeta.getGuid(),
                null);
    }


    public static NewsViewModel mapItemLenta(ItemLenta itemLenta) {

        return new NewsViewModel(
                "gazeta.ru",
                itemLenta.getTitle().trim(),
                itemLenta.getDescription().trim(),
                getDateOrDie(itemLenta.getPubDate()),
                itemLenta.getGuid(),
                getDateOrDie(itemLenta.getEnclosure())
        );


    }

    private static String getDateOrDie(Enclosure enclosure) {
        if (enclosure != null) {
            return enclosure.getUrl();
        } else {
            return null;
        }
    }


    private static Long getDateOrDie(String date) {
        try {
            return dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }


}
