package com.lardis.ivan.newsreader.ui.fragment;


import com.lardis.ivan.newsreader.R;
import com.lardis.ivan.newsreader.model.app.NewsViewModel;
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

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private SimpleDateFormat mFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    private List<NewsViewModel> mModels = new ArrayList<>();

    public void setData(List<NewsViewModel> data) {
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
            itemView.setOnClickListener(v1 -> onClickListener.click(getLayoutPosition()));

        }


    }

    interface OnClickListener {

        void click(int position);
    }

    private OnClickListener mOnClickListener = position -> {
        NewsViewModel newsViewModel = mModels.get(position);
        newsViewModel.setShowDescription(!newsViewModel.isShowDescription());
        this.notifyItemChanged(position);

    };

    public NewsAdapter() {
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
            int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_fragment_item, parent, false);
        return new ViewHolder(v, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        NewsViewModel newsViewModel = mModels.get(position);
        holder.title.setText(newsViewModel.getTitle());
        holder.dateLong.setText(mFormat.format(newsViewModel.getDateLong()));
        holder.origin.setText(newsViewModel.getOrigin());
        holder.description.setText(newsViewModel.getDescription());
        holder.description.setVisibility(newsViewModel.isShowDescription() ? View.VISIBLE : View.GONE);

        if (newsViewModel.getUrlPhoto() != null && !newsViewModel.getUrlPhoto().equals("")) {
            holder.image.setVisibility(View.VISIBLE);
            Picasso.with(holder.image.getContext())
                    .load(newsViewModel.getUrlPhoto())
                    .placeholder(R.drawable.ic_crop_original_black_48dp)
                    .error(R.drawable.ic_crop_original_black_48dp)
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