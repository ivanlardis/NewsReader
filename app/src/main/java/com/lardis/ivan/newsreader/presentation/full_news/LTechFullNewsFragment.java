package com.lardis.ivan.newsreader.presentation.full_news;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.lardis.ivan.newsreader.R;
import com.lardis.ivan.newsreader.business.model.app.LTechModel;
import com.lardis.ivan.newsreader.presentation.navigation.ToggleView;
import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import timber.log.Timber;


public class LTechFullNewsFragment extends MvpAppCompatFragment  {

    private SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.full_news_fragment, container, false);
        tvDate = (TextView) view.findViewById(R.id.full_news_fragment_text_view_dateLong);
        tvTitle = (TextView) view.findViewById(R.id.full_news_fragment_text_view_title);
        iVPhoto = (ImageView) view.findViewById(R.id.full_news_fragment_image);
        tvText = (TextView) view.findViewById(R.id.full_news_fragment_text_view_description);
        return view;



    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
           LTechModel mLTechModel=null;
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

        ( getActivity()).setTitle(mLTechModel.getTitle());
        tvTitle.setText(mLTechModel.getTitle());
        tvDate.setText(mFormat.format(mLTechModel.getTimestump()));
        tvText.setText(mLTechModel.getText());
//                iVPhoto

        if (mLTechModel.getImageUrl() != null && !mLTechModel.getImageUrl().equals("")) {
            iVPhoto.setVisibility(View.VISIBLE);
            Picasso.with(getContext())
                    .load(mLTechModel.getImageUrl())
                    .placeholder(R.drawable.ic_crop_original_black_48dp)
                    .error(R.drawable.ic_report_black_48dp)
                    .resize(120, 90)
                    .centerCrop()
                    .into(iVPhoto);

        } else {
            iVPhoto.setVisibility(View.GONE);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ((ToggleView) getActivity()).setToggleAsBack(true);
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDefaultDisplayHomeAsUpEnabled(true);
            ((ToggleView) getActivity()).setToggleAsBack(true);
        }

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ActionBar supportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setDefaultDisplayHomeAsUpEnabled(false);
            ((ToggleView) getActivity()).setToggleAsBack(false);
        }
    }


}
