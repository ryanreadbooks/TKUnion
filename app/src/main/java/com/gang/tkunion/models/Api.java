package com.gang.tkunion.models;

import com.gang.tkunion.models.pojos.Categories;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("discovery/categories")
    Call<Categories> accessCategories();

}
