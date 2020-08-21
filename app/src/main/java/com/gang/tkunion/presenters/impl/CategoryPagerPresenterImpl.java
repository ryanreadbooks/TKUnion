package com.gang.tkunion.presenters.impl;

import androidx.annotation.NonNull;

import com.gang.tkunion.models.Api;
import com.gang.tkunion.models.pojos.CategoryConcreteContent;
import com.gang.tkunion.presenters.interfaces.ICategoryPagerPresenter;
import com.gang.tkunion.ui.callbacks.ICategoryPagerCallback;
import com.gang.tkunion.utils.RetrofitManager;
import com.gang.tkunion.utils.UrlUtils;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * 分类的商品的获取的Presenter
 * 所有的分类信息公用一个Presenter，也就是这个Presenter
 * 避免多个分类的商品生成多个Presenter对象
 * 所以写成单例模式
 */
public class CategoryPagerPresenterImpl implements ICategoryPagerPresenter {

    private ICategoryPagerCallback mCallback;

    private CategoryPagerPresenterImpl() {
    }

    private static ICategoryPagerPresenter sInstance;

    public static ICategoryPagerPresenter getInstance() {
        if (sInstance == null) {
            synchronized (CategoryPagerPresenterImpl.class) {
                if (sInstance == null) {
                    sInstance = new CategoryPagerPresenterImpl();
                }
            }
        }
        return sInstance;
    }

    /**
     * 提供给外部的获取数据的接口
     * 用来给外部获取分类商品的具体信息
     * 通过分类商品的id来进行获取
     *
     * @param materialId 分类id
     */
    @Override
    public void getCategoryContentById(int materialId) {
        if (mCallback != null) {
            mCallback.onLoading(materialId);
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        String contentUrl = UrlUtils.createCategoryContentUrl(materialId, 1);
        Call<CategoryConcreteContent> call = api.accessCategoryConcreteContent(contentUrl);
        call.enqueue(new Callback<CategoryConcreteContent>() {
            @Override
            public void onResponse(@NonNull Call<CategoryConcreteContent> call, @NonNull Response<CategoryConcreteContent> response) {
                if (mCallback != null) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        CategoryConcreteContent body = response.body();
                        if (body != null) {
                            // 加载成功
                            mCallback.onContentLoaded(body.getData());
                        }
                        else {
                            // 数据为空
                            mCallback.onEmpty(materialId);
                        }
                    }
                    else {
                        // 加载失败
                        mCallback.onError(materialId);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<CategoryConcreteContent> call, @NonNull Throwable t) {
                if (mCallback != null) {
                    mCallback.onError(materialId);
                }
            }
        });
    }

    @Override
    public void loadMoreContentById(int materialId) {

    }

    @Override
    public void reload(int materialId) {

    }

    @Override
    public void registerUICallback(ICategoryPagerCallback callback) {
        mCallback = callback;

    }

    @Override
    public void unregisterUICallback(ICategoryPagerCallback callback) {
        mCallback = null;
    }
}
