package com.example.foodplanner.network;

import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET("categories.php")
    Observable<MealResponse> getCategories();
    @GET("random.php")
    Observable<MealResponse> getMeal();
    @GET("filter.php")
    Observable<MealResponse> getMealByCategory(@Query("c")String category);
    @GET("lookup.php")
    Observable<MealResponse> getMealById(@Query("i")String strMeal);
    @GET("list.php?a=list")
    Observable<MealResponse>getAllCountries();
    @GET("filter.php?")
    Observable<MealResponse>getMealByCountry(@Query("a")String strArea);


}
