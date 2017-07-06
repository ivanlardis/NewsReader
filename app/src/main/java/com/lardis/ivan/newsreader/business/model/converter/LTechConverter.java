package com.lardis.ivan.newsreader.business.model.converter;

import com.lardis.ivan.newsreader.business.model.app.LTechModel;
import com.lardis.ivan.newsreader.data.network.model.LTechModelNW;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import timber.log.Timber;


public class LTechConverter {

    private static final String TAG = "LTechConverter";


    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");

    public static LTechModel mapItemLTech(LTechModelNW item) {
        Log.e(TAG, "mapItemLTech: "+  item.getId()+ "1111");
        Log.e(TAG, "mapItemLTech: "+  item.getSort()+ "1111");
        Log.e(TAG, "mapItemLTech: "+  item.getTitle()+ "1111");
        Log.e(TAG, "mapItemLTech: "+  item.getText()+ "1111");
        Log.e(TAG, "mapItemLTech: "+  item.getDate()+ "1111");
        Log.e(TAG, "mapItemLTech: "+  item.getImage()+ "1111");
        return new LTechModel(
                item.getId(),
                item.getTitle().trim(),
                item.getText().trim(),
                item.getImage(),
                item.getSort(),
                getDateOrDie(item.getDate())
        );
    }


    private static Long getDateOrDie(String date) {
        try {
            return dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            Timber.e("Ошибка парса даты");
            e.printStackTrace();
        }
        return 0L;
    }


}
