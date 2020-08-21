package com.gang.tkunion.models;

import com.gang.tkunion.models.pojos.Categories;
import com.gang.tkunion.models.pojos.CategoryConcreteContent;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    @GET("discovery/categories")
    Call<Categories> accessCategories();

     // 获取分类商品详情的Url"discovery/{materialId}/{page}"
    @GET
    Call<CategoryConcreteContent> accessCategoryConcreteContent(@Url String url);
}
