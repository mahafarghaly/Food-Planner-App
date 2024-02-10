package com.example.foodplanner.network;

import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("categories.php")
    Call<MealResponse> getCategories();
    @GET("random.php")
    Call<MealResponse> getRandomMeal();
}
