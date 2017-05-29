package com.lardis.ivan.newsreader.ui.fragment;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lardis.ivan.newsreader.R;
import com.lardis.ivan.newsreader.model.app.NewsViewModel;
import com.lardis.ivan.newsreader.presentation.NewsPresenter;
import com.lardis.ivan.newsreader.presentation.NewsView;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by black-sony on 25.05.17.
 */

public class NewsFragment extends MvpAppCompatFragment implements NewsView {

    private static final String TAG = "NewsFragment";

    @InjectPresenter
    NewsPresenter mNavigationPresenter;

    private SwipeRefreshLayout mRefreshLayout;

    private NewsAdapter adapter;

    private FrameLayout mFrameLayout;

    private RecyclerView mRecyclerView;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {

        return new NewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_fragment, container, false);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.news_fragment_swipe_container);
        mFrameLayout = (FrameLayout) view.findViewById(R.id.news_fragment_frame_layout_warning);
        mRefreshLayout.setOnRefreshListener(() -> mNavigationPresenter.loadData());
        initRecyclerView(view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (menu != null && menu.size() == 1) {
        } else {
            inflater.inflate(R.menu.news_fragment_menu, menu);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.item_update) {
            mNavigationPresenter.loadData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecyclerView(final View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_fragment_recycler);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView
                .addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        adapter = new NewsAdapter();
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void showData(final List<NewsViewModel> newsViewModels) {
        adapter.setData(newsViewModels);
    }

    @Override
    public void showWarning(final boolean show) {
        mFrameLayout.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showProgress(final boolean show) {
        mRefreshLayout.setRefreshing(show);
    }
}
