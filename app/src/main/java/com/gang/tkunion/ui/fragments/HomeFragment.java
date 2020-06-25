package com.gang.tkunion.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.gang.tkunion.R;
import com.gang.tkunion.base.BaseFragment;
import com.gang.tkunion.models.pojos.Categories;
import com.gang.tkunion.presenters.impl.HomePresenterImpl;
import com.gang.tkunion.presenters.interfaces.IHomePresenter;
import com.gang.tkunion.ui.callbacks.IHomeCallback;
import com.gang.tkunion.utils.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment implements IHomeCallback {

    private static final String TAG = "HomeFragment";
    private IHomePresenter mPresenter;

    @BindView(R.id.mainHomeTab)
    TabLayout mTabLayout;
    @BindView(R.id.mainHomePager)
    ViewPager2 mHomePager;
    private Unbinder mUnBinder;
    private TabLayoutMediator mMediator;
    private List<CategoryDetailFragment> mDetailFragmentList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        initPresenters();
    }

    /**
     * HomeFragment的特有根布局 与BaseFragment的默认根布局不一样
     * @param inflater inflater
     * @param container container
     * @return HomeFragment的特有根布局
     */
    @Override
    protected View loadRootView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.fragment_base_fragment_home_special, container, false);
    }

    @Override
    protected int setSuccessViewId() {
        return R.layout.fragment_home_success;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        LogUtils.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        // 页面已经加载成功 开始加载数据
        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d(TAG, "onResume");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews(View view) {
        mUnBinder = ButterKnife.bind(this, view);
    }

    @Override
    public void initPresenters() {
        mPresenter = new HomePresenterImpl();
        mPresenter.registerUICallback(this);
    }

    @Override
    public void loadData() {
        if (mPresenter != null) {
            // 向Presenter请求加载数据
            mPresenter.getCategories();
        }
    }

    /**
     * 异步加载成功的数据在这里回来
     *
     * @param categories 商品分类数据
     */
    @Override
    public void onCategoriesLoaded(Categories categories) {
        // 设置加载成功的页面
        setStatus(Status.SUCCESS);
        LogUtils.d(TAG, "code : " + categories.getCode());
        LogUtils.d(TAG, "message : " + categories.getMessage());
        LogUtils.d(TAG, "isSuccess : " + categories.isSuccess());
        final List<Categories.Category> categoriesData = categories.getData();
        LogUtils.d(TAG, "data : " + categoriesData);
        // 创建新的Fragment
        if (mDetailFragmentList == null) {
            mDetailFragmentList = new ArrayList<>();
        }
        if (mDetailFragmentList.size() == 0) {
            // 通过循环创建所有的fragments 有多少分类就创建多少个Fragments
            for (int i = 0; i < categoriesData.size(); i++) {
                mDetailFragmentList.add(CategoryDetailFragment.newInstance(categoriesData.get(i)));
            }
        }
        // 设置TabLayout和ViewPager
        mHomePager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                // 生成商品分类详细信息的页面
                return mDetailFragmentList.get(position);
            }

            @Override
            public int getItemCount() {
                return categoriesData.size();
            }
        });
        mMediator = new TabLayoutMediator(mTabLayout, mHomePager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(categoriesData.get(position).getTitle());
            }
        });
        mMediator.attach();
    }

    /**
     * 正在加载
     */
    @Override
    public void onLoading() {
        // 设置正在加载的页面
        setStatus(Status.LOADING);
    }

    /**
     * 加载错误
     */
    @Override
    public void onError() {
        // 设置加载错误的页面
        setStatus(Status.ERROR);
    }

    @Override
    protected void retryLoadData() {
        // 再次显示加载页面
        setStatus(Status.LOADING);
        // 重新加载数据
        loadData();
    }

    /**
     * 加载的内容为空
     */
    @Override
    public void onEmpty() {
        // 设置内容为空的页面
        setStatus(Status.EMPTY);
    }

    @Override
    protected void release() {
        if (isRemoving()) {
            if (mPresenter != null) {
                mPresenter.unregisterUICallback(this);
                mPresenter = null;
            }
            if (mUnBinder != null) {
                mUnBinder.unbind();
                mUnBinder = null;
            }
            if (mMediator != null) {
                mMediator.detach();
                mMediator = null;
            }
            if (mDetailFragmentList != null) {
                mDetailFragmentList.clear();
                mDetailFragmentList = null;
            }
        }
    }
}
