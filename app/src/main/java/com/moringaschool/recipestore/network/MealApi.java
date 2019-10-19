package com.moringaschool.recipestore.network;

import com.moringaschool.recipestore.models.Store;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealApi {

    @GET("businesses/search")
    Call<Store> getMeals(
            @Query("s") String foodName
    );
}
