package com.gang.tkunion.ui.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gang.tkunion.R;
import com.gang.tkunion.base.BaseFragment;
import com.gang.tkunion.models.pojos.Categories;
import com.gang.tkunion.models.pojos.CategoryConcreteContent;
import com.gang.tkunion.presenters.impl.CategoryPagerPresenterImpl;
import com.gang.tkunion.presenters.interfaces.ICategoryPagerPresenter;
import com.gang.tkunion.ui.callbacks.ICategoryPagerCallback;
import com.gang.tkunion.utils.Constants;
import com.gang.tkunion.utils.LogUtils;

import java.util.List;

public class CategoryDetailFragment extends BaseFragment implements ICategoryPagerCallback {

    private static final String TAG = "CategoryDetailFragment";
    private ICategoryPagerPresenter mPresenter;

    /**
     * 创建新的CategoryDetailFragment
     *
     * @param category 分类实例
     * @return CategoryDetailFragment
     */
    public static CategoryDetailFragment newInstance(Categories.Category category) {
        CategoryDetailFragment categoryDetailFragment = new CategoryDetailFragment();
        Bundle bundle = new Bundle();
        int materialId = category.getId();
        String title = category.getTitle();
        bundle.putInt(Constants.KEY_NEW_CATEGORY_FRAGMENT_ID, materialId);
        bundle.putString(Constants.KEY_NEW_CATEGORY_FRAGMENT_TITLE, title);
        categoryDetailFragment.setArguments(bundle);
        return categoryDetailFragment;
    }

    @Override
    protected int setSuccessViewId() {
        return R.layout.fragment_category_detail;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 加载数据
        loadData();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initViews(View view) {
        setStatus(Status.SUCCESS);
    }

    @Override
    public void initPresenters() {
        mPresenter = CategoryPagerPresenterImpl.getInstance();
        mPresenter.registerUICallback(this);
    }

    @Override
    public void loadData() {
        // 创建新的实例时设置了参数 从这里可以取出来
        Bundle bundle = getArguments();
        if (bundle != null) {
            int materialId = bundle.getInt(Constants.KEY_NEW_CATEGORY_FRAGMENT_ID);
            String title = bundle.getString(Constants.KEY_NEW_CATEGORY_FRAGMENT_TITLE);
            LogUtils.d(TAG, "loadData: == id " + materialId);
            LogUtils.d(TAG, "loadData: == title " + title);
            // 通过presenter申请加载出局
            mPresenter.getCategoryContentById(materialId);
        }
    }

    @Override
    protected void release() {
        if (mPresenter != null) {
            mPresenter.unregisterUICallback(this);
        }
    }

    @Override
    protected void retryLoadData() {
        // 点击重新加载回再这里回调 BaseFragment
    }

    /**
     * 成功加载了分类商品的详细内容
     * @param contents 成功加载的内容列表
     */
    @Override
    public void onContentLoaded(List<CategoryConcreteContent.ConcreteContent> contents) {

    }

    /**
     * 类商品的详细内容为空
     * @param materialId 加载的分类id
     */
    @Override
    public void onEmpty(int materialId) {

    }

    /**
     * 加载分类商品详细信息出错
     * @param materialId 加载的分类id
     */
    @Override
    public void onError(int materialId) {

    }

    /**
     * 正在加载分类商品详细信息
     * @param materialId 加载的分类id
     */
    @Override
    public void onLoading(int materialId) {

    }

    /**
     * 加载更多分类商品详细信息出错
     * @param materialId 加载的分类id
     */
    @Override
    public void onLoadMoreError(int materialId) {

    }

    /**
     * 加载更多分类商品信息 结果为空
     * @param materialId 加载的分类id
     */
    @Override
    public void onLoadMoreEmpty(int materialId) {

    }

    /**
     * 加载更多分类商品信息陈工
     * @param moreContents 更多数据
     */
    @Override
    public void onLoadMoreLoaded(List<CategoryConcreteContent.ConcreteContent> moreContents) {

    }

    /**
     * 分类商品信息的轮播图加载成功
     * @param moreContents 内容
     */
    @Override
    public void onLooperListLoaded(List<CategoryConcreteContent.ConcreteContent> moreContents) {

    }
}
