package com.gang.tkunion.ui.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gang.tkunion.R;
import com.gang.tkunion.base.BaseFragment;
import com.gang.tkunion.models.pojos.Categories;
import com.gang.tkunion.utils.Constants;
import com.gang.tkunion.utils.LogUtils;

public class CategoryDetailFragment extends BaseFragment {

    private static final String TAG = "CategoryDetailFragment";
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
        }
    }

    @Override
    protected void release() {

    }

    @Override
    protected void retryLoadData() {

    }
}
