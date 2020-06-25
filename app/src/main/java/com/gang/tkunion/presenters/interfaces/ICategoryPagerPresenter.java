package com.gang.tkunion.presenters.interfaces;

import com.gang.tkunion.base.IBasePresenter;
import com.gang.tkunion.models.pojos.CategoryConcreteContent;
import com.gang.tkunion.ui.callbacks.ICategoryPagerCallback;

import java.util.List;

/**
 * 主页HomeFragment里面的ViewPager里面的Fragment所需要的数据加载的Presenter
 */
public interface ICategoryPagerPresenter extends IBasePresenter<ICategoryPagerCallback> {

    /**
     * 通过分类id加载分类详细内容
     * @param materialId 分类id
     */
    void getCategoryContentById(int materialId);

    /**
     * 通过分类id加载更多商品内容
     * @param materialId 分类id
     */
    void loadMoreContentById(int materialId);

    void reload(int materialId);

}
