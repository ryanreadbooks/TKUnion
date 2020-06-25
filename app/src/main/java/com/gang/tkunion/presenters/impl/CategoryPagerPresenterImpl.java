package com.gang.tkunion.presenters.impl;

import com.gang.tkunion.presenters.interfaces.ICategoryPagerPresenter;
import com.gang.tkunion.ui.callbacks.ICategoryPagerCallback;

/**
 * 分类的商品的获取的Presenter
 * 所有的分类信息公用一个Presenter，也就是这个Presenter
 * 避免多个分类的商品生成多个Presenter对象
 */
public class CategoryPagerPresenterImpl implements ICategoryPagerPresenter {
    @Override
    public void getCategoryContentById(int materialId) {

    }

    @Override
    public void loadMoreContentById(int materialId) {

    }

    @Override
    public void reload(int materialId) {

    }

    @Override
    public void registerUICallback(ICategoryPagerCallback callback) {

    }

    @Override
    public void unregisterUICallback(ICategoryPagerCallback callback) {

    }
}
