package com.lardis.ivan.newsreader.presentation.news;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lardis.ivan.newsreader.R;
import com.lardis.ivan.newsreader.business.model.app.LTechModel;
import com.lardis.ivan.newsreader.di.DI;

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

import javax.inject.Inject;

import ru.terrakok.cicerone.Router;

import static com.lardis.ivan.newsreader.presentation.navigation.NavigationScreens.FULL_NEWS;

/**
 * Created by black-sony on 25.05.17.
 */

public class LTechNewsFragment extends MvpAppCompatFragment implements LTechNewsView {

    private static final String TAG = "LTechNewsFragment";

    @InjectPresenter
    LTechNewsPresenter mNavigationPresenter;

    @Inject
    Router mRouter;

    private SwipeRefreshLayout mRefreshLayout;

    private LTechNewsAdapter adapter;

    private FrameLayout mFrameLayout;

    private RecyclerView mRecyclerView;

    public LTechNewsFragment() {
    }

    public static LTechNewsFragment newInstance() {

        return new LTechNewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        DI.getInstance().componentManager().navigationComponent().inject(this);

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
        adapter = new LTechNewsAdapter(lTechModel -> {
            mRouter.navigateTo(FULL_NEWS, lTechModel);
        });
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void showData(final List<LTechModel> newsViewModels) {
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
