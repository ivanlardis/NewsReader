package com.lardis.ivan.newsreader.ui.actvity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lardis.ivan.newsreader.R;
import com.lardis.ivan.newsreader.di.DI;
import com.lardis.ivan.newsreader.presentation.NavigationPresenter;
import com.lardis.ivan.newsreader.presentation.NavigationsView;
import com.lardis.ivan.newsreader.ui.NavigationScreens;
import com.lardis.ivan.newsreader.ui.fragment.NewsFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

public class NavigationActivity extends MvpAppCompatActivity implements NavigationsView,
        NavigationView.OnNavigationItemSelectedListener {

    private int ITEM_NEWS = 0;

    private ActionBarDrawerToggle toggle;

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    @InjectPresenter
    NavigationPresenter mNavigationPresenter;

    @Inject
    Router mRouter;

    @Inject
    NavigatorHolder navigatorHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DI.getInstance().componentManager().navigationComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        initView();

        if (savedInstanceState == null) {
            navigationView.getMenu().getItem(ITEM_NEWS).setChecked(true);
            onNavigationItemSelected(navigationView.getMenu().getItem(ITEM_NEWS));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_news:
                mNavigationPresenter.onNewRootCommandClick(NavigationScreens.NEWS);
                break;

            case R.id.nav_exit:
                mNavigationPresenter.onExit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    SupportFragmentNavigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(),
            R.id.navigation_activity_layout_content_container) {
        @Override
        protected Fragment createFragment(final String screenKey, final Object data) {
            switch (screenKey) {
                case NavigationScreens.NEWS:
                    return NewsFragment.newInstance();
                default:
                    return NewsFragment.newInstance();
            }
        }

        @Override
        protected void showSystemMessage(final String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onPostCreate(@Nullable final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    protected void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @ProvidePresenter
    public NavigationPresenter provideRepositoryPresenter() {
        return new NavigationPresenter(mRouter);
    }


    public void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.navigation_activity_layout_content_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_activity_drawer_layout);
        navigationView = (NavigationView) findViewById(
                R.id.navigation_activity_navigation_view);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, 0, 0);
        drawerLayout.addDrawerListener(toggle);
        toggle.setToolbarNavigationClickListener(v -> mNavigationPresenter.onBack());
        navigationView.setNavigationItemSelectedListener(this);

    }


}
