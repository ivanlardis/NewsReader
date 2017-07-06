package com.lardis.ivan.newsreader.presentation.full_news;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.lardis.ivan.newsreader.R;
import com.lardis.ivan.newsreader.business.model.app.LTechModel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by black-sony on 25.05.17.
 */

public class LTechFullNewsFragment extends MvpAppCompatFragment  {

    private SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private TextView tvOrigin;
    private TextView tvDate;
    private TextView tvTitle;
    private TextView tvText;
    private ImageView iVPhoto;

    public static LTechFullNewsFragment newInstance(Object lTechModel) {
        LTechFullNewsFragment lTechFullNewsFragment = new LTechFullNewsFragment();
        Bundle bundle=new Bundle();

        try {
            bundle.putParcelable(LTechModel.class.getSimpleName(),(LTechModel)lTechModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lTechFullNewsFragment.setArguments(bundle);

        return lTechFullNewsFragment;
    }
private  LTechModel mLTechModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.full_news_fragment, container, false);
        tvOrigin = (TextView) view.findViewById(R.id.full_news_fragment_text_view_origin);
        tvDate = (TextView) view.findViewById(R.id.full_news_fragment_text_view_dateLong);
        tvTitle = (TextView) view.findViewById(R.id.full_news_fragment_text_view_title);
        iVPhoto = (ImageView) view.findViewById(R.id.full_news_fragment_image);
        tvText = (TextView) view.findViewById(R.id.full_news_fragment_text_view_description);
        return view;



    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
           mLTechModel= getArguments().getParcelable(LTechModel.class.getSimpleName());
        }
        if(mLTechModel!=null)
        {
            show(mLTechModel);
        }


    }
    public void show(LTechModel mLTechModel)
    {

        tvOrigin.setText(String.valueOf(mLTechModel.getSort()));
        tvTitle.setText(mLTechModel.getTitle());
        tvDate.setText(mFormat.format(mLTechModel.getTimestump()));
        tvText.setText(mLTechModel.getText());
//                iVPhoto




    }
}
