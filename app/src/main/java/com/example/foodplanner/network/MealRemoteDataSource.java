package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.RandomMeal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {
    public static String TAG="TAG";
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private static MealRemoteDataSource client=null;

    private MealRemoteDataSource(){

    }
    public static MealRemoteDataSource getInstance(){
        if(client==null){
            client=new MealRemoteDataSource();
        }
        return client;
    }
    public void makeNetworkCall(NetworkCallback networkCallback){
        Gson gson=new GsonBuilder().setLenient().create();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
       MealService mealService=retrofit.create(MealService.class);
        Call<MealResponse> call=mealService.getCategories();

        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if(response.isSuccessful()){
                    networkCallback.onSuccessResult(response.body().getCategories());
                    Log.i(TAG, "onResponse: "+response.body());
                }


            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());
                Log.i(TAG, "onFailure: "+t.getMessage());


            }
        });

    }
     public void randomNetworkCall(NetworkCallback networkCallback){
         Gson gson=new GsonBuilder().setLenient().create();
         Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
         MealService mealService=retrofit.create(MealService.class);
         Call<MealResponse> call=mealService.getRandomMeal();
         call.enqueue(new Callback<MealResponse>(){

             @Override
             public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                 if(response.isSuccessful()){
//                  //  networkCallback.onSuccessResult(response.body().getRandomMeal());
//                     Log.i(TAG, "onResponse Random Mail: "+response.body());
//                 }
                      if(response.isSuccessful()){
                         List<RandomMeal> randomMeals = response.body().getRandomMeal();
                         // Log response data
                         if (randomMeals != null) {
                             Log.d(TAG, "Random Meals:");
                             for (RandomMeal meal : randomMeals) {
                                 Log.d(TAG, meal.toString());
                             }
                         } else {
                             Log.d(TAG, "Random Meals: null");
                         }
                         // Now you can pass the randomMeals to the callback
                         networkCallback.onSuccessResult(randomMeals);
                     } else {
                         Log.e(TAG, "Response not successful: " + response.code());
                     }
                 }





                 @Override
             public void onFailure(Call<MealResponse> call, Throwable t) {
                 networkCallback.onFailureResult(t.getMessage());
                 Log.i(TAG, "onFailure Random Mail: "+t.getMessage());

             }
         });

     }

}
