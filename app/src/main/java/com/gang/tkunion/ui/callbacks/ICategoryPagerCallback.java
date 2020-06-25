package com.gang.tkunion.ui.callbacks;

import com.gang.tkunion.models.pojos.CategoryConcreteContent;

import java.util.List;

/**
 * 主页HomeFragment里面的ViewPager里面的Fragment所需要的数据加载的回调接口
 */
public interface ICategoryPagerCallback {

    /**
     * 各个分类的数据加载成功
     *
     * @param contents 成功加载的内容列表
     */
    void onContentLoaded(List<CategoryConcreteContent.ConcreteContent> contents);

    /**
     * 对应的分类加载结果为空
     *
     * @param materialId 加载的分类id
     */
    void onEmpty(int materialId);

    /**
     * 对应的分类加载失败
     *
     * @param materialId 加载的分类id
     */
    void onError(int materialId);

    /**
     * 对应的分类正在加载
     *
     * @param materialId 加载的分类id
     */
    void onLoading(int materialId);

    /**
     * 加载更多失败
     * @param materialId 加载的分类id
     */
    void onLoadMoreError(int materialId);

    /**
     * 加载不到更多了
     * @param materialId 加载的分类id
     */
    void onLoadMoreEmpty(int materialId);

    /**
     * 成功加载到了更多数据
     * @param moreContents 更多数据
     */
    void onLoadMoreLoaded(List<CategoryConcreteContent.ConcreteContent> moreContents);

    /**
     * 轮播图加载成功
     * @param moreContents 内容
     */
    void onLooperListLoaded(List<CategoryConcreteContent.ConcreteContent> moreContents);
}
