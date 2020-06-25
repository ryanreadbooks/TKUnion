package com.gang.tkunion.presenters.impl;


import androidx.annotation.NonNull;

import com.gang.tkunion.models.Api;
import com.gang.tkunion.models.pojos.Categories;
import com.gang.tkunion.presenters.interfaces.IHomePresenter;
import com.gang.tkunion.ui.callbacks.IHomeCallback;
import com.gang.tkunion.utils.RetrofitManager;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * HomeFragment的Presenter
 */
public class HomePresenterImpl implements IHomePresenter {

    private IHomeCallback mHomeCallback;

    /**
     * 对外提供的获取商品分类的请求接口方法
     */
    @Override
    public void getCategories() {
        if (mHomeCallback != null) {
            mHomeCallback.onLoading();
        }
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        Api api = retrofit.create(Api.class);
        Call<Categories> call = api.accessCategories();
        call.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call, @NonNull Response<Categories> response) {
                if (mHomeCallback != null) {
                    Categories categories = response.body();
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        if (categories == null || categories.getData().size() == 0) {
                            // 数据为空
                            mHomeCallback.onEmpty();
                        }
                        else {
                            // 加载成功
                            mHomeCallback.onCategoriesLoaded(categories);
                        }
                    } else {
                        // 请求错误
                        mHomeCallback.onError();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {
                // 请求错误
                if (mHomeCallback != null) {
                    mHomeCallback.onError();
                }
            }
        });
    }

    @Override
    public void registerUICallback(IHomeCallback callback) {
        mHomeCallback = callback;
    }

    @Override
    public void unregisterUICallback(IHomeCallback callback) {
        mHomeCallback = null;
    }
}
