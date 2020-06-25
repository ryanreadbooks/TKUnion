package com.gang.tkunion.ui.callbacks;

import com.gang.tkunion.models.pojos.Categories;

public interface IHomeCallback {

    /**
     * 加载中
     */
    void onLoading();

    /**
     * 商品分类数据返回
     *
     * @param categories 商品分类数据
     */
    void onCategoriesLoaded(Categories categories);

    /**
     * 加载错误
     */
    void onError();

    /**
     * 数据为空
     */
    void onEmpty();

}
