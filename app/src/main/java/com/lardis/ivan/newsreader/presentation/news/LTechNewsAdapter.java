package com.lardis.ivan.newsreader.presentation.news;


import com.lardis.ivan.newsreader.R;
import com.lardis.ivan.newsreader.business.model.app.LTechModel;
import com.lardis.ivan.newsreader.utils.OnClickListen;
import com.squareup.picasso.Picasso;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.lardis.ivan.newsreader.R.id.news_fragment_item_text_view_description;

/**
 * Created by i_larin on 26.03.17.
 */

public class LTechNewsAdapter extends RecyclerView.Adapter<LTechNewsAdapter.ViewHolder> {

    private SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private List<LTechModel> mModels = new ArrayList<>();

    public void setData(List<LTechModel> data) {
        mModels = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        TextView description;

        TextView dateLong;

        TextView origin;

        ImageView image;

        public ViewHolder(View v, OnClickListener onClickListener) {
            super(v);
            origin = (TextView) v.findViewById(R.id.news_fragment_item_text_view_origin);
            title = (TextView) v.findViewById(R.id.news_fragment_item_text_view_title);
            description = (TextView) v.findViewById(news_fragment_item_text_view_description);
            dateLong = (TextView) v.findViewById(R.id.news_fragment_item_text_view_dateLong);
            image = (ImageView) v.findViewById(R.id.news_fragment_item_image);
            itemView.setOnClickListener(v1 -> {

                if (onClickListener != null) {

                    onClickListener.click(getLayoutPosition());

                }
            });

        }


    }

    interface OnClickListener {

        void click(int position);
    }


    private OnClickListener mOnClickListener= new OnClickListener() {
        @Override
        public void click(final int position) {
            if (mLTechModelOnClickListen != null) {
                mLTechModelOnClickListen.click(mModels.get(position));
            }
        }
    };

    private OnClickListen<LTechModel> mLTechModelOnClickListen;

    public LTechNewsAdapter(OnClickListen<LTechModel> onClickListener) {
        this.mLTechModelOnClickListen = onClickListener;
    }


    @Override
    public LTechNewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_fragment_item, parent, false);
        return new ViewHolder(v, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        LTechModel newsViewModel = mModels.get(position);
        holder.title.setText(newsViewModel.getTitle());
        holder.dateLong.setText(mFormat.format(newsViewModel.getTimestump()));
        holder.origin.setText("sort= " + newsViewModel.getSort());
        holder.description.setText(newsViewModel.getText());

        if (newsViewModel.getImageUrl() != null && !newsViewModel.getImageUrl().equals("")) {
            holder.image.setVisibility(View.VISIBLE);
            Picasso.with(holder.image.getContext())
                    .load(newsViewModel.getImageUrl())
                    .placeholder(R.drawable.ic_crop_original_black_48dp)
                    .error(R.drawable.ic_report_black_48dp)
                    .resize(120, 90)
                    .centerCrop()
                    .into(holder.image);

        } else {
            holder.image.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }
}