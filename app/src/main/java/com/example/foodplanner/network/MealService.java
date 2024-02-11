package com.example.foodplanner.network;

import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("categories.php")
    Call<MealResponse> getCategories();
    @GET("random.php")
    Call<MealResponse> getMeal();
    @GET("filter.php")
    Call<MealResponse> getMealByCategory(@Query("c")String category);
    @GET("lookup.php")
    Call<MealResponse> getMealById(@Query("i")String strMeal);


}
