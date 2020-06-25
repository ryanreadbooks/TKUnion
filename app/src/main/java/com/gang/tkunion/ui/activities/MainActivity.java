package com.gang.tkunion.ui.activities;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gang.tkunion.R;
import com.gang.tkunion.base.BaseActivity;
import com.gang.tkunion.base.BaseFragment;
import com.gang.tkunion.ui.fragments.HomeFragment;
import com.gang.tkunion.ui.fragments.RecommendationFragment;
import com.gang.tkunion.ui.fragments.RedPacketFragment;
import com.gang.tkunion.ui.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private HomeFragment mHomeFragment;
    private RecommendationFragment mRecommendationFragment;
    private RedPacketFragment mRedPacketFragment;
    private SearchFragment mSearchFragment;
    private BaseFragment mCurrentFragment;  // 当前正在显示的Fragment
    private boolean[] addedFragmentFlag = new boolean[4];    // 记录哪几个fragment已经added过了

    @BindView(R.id.mainBottomNav)
    BottomNavigationView mBottomNav;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments();
        initEvents();
        initBroadcasts();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mRecommendationFragment = new RecommendationFragment();
        mRedPacketFragment = new RedPacketFragment();
        mSearchFragment = new SearchFragment();
        mCurrentFragment = mHomeFragment;   // 默认当前fragment时HomeFragment
        // 默认显示首页HomeFragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainFragmentContainer, mHomeFragment)
                .show(mHomeFragment)
                .commit();
        addedFragmentFlag[0] = true;
    }

    /**
     * 切换Fragment
     * @param index 页面index(0~3)
     * @param targetFragment 需要切到的目标的fragment
     */
    private void switchFragments(int index, BaseFragment targetFragment) {
        if (addedFragmentFlag[index]) {
            // 这个fragment已经被添加过了
            getSupportFragmentManager().beginTransaction()
                    .hide(mCurrentFragment)
                    .show(targetFragment)
                    .commit();
        }
        else {
            // 这个fragment还没有被添加过 需要add 再添加
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.mainFragmentContainer, targetFragment)
                    .hide(mCurrentFragment).show(targetFragment)
                    .commit();
            addedFragmentFlag[index] = true;
        }
    }

    @Override
    public void initViews() {
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    public void initEvents() {
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_home:
                        switchFragments(0, mHomeFragment);
                        mCurrentFragment = mHomeFragment;
                        break;
                    case R.id.bottom_recommendation:
                        switchFragments(1, mRecommendationFragment);
                        mCurrentFragment = mRecommendationFragment;
                        break;
                    case R.id.bottom_red_packet:
                        switchFragments(2, mRedPacketFragment);
                        mCurrentFragment = mRedPacketFragment;
                        break;
                    case R.id.bottom_search:
                        switchFragments(3, mSearchFragment);
                        mCurrentFragment = mSearchFragment;
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    @Override
    public void initBroadcasts() {

    }

    @Override
    protected void onStop() {
        super.onStop();
        release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }

    @Override
    public void release() {
        if (isFinishing()) {
            if (mUnBinder != null) {
                mUnBinder.unbind();
                mUnBinder = null;
            }
        }
    }
}