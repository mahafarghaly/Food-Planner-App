package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.Meal;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSource {
    public static String TAG="TAG";
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private static MealRemoteDataSource client=null;
    private Retrofit retrofit;
    private MealService mealService;
    private MealRemoteDataSource(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        mealService = retrofit.create(MealService.class);
    }
    public static MealRemoteDataSource getInstance(){
        if(client==null){
            client=new MealRemoteDataSource();
        }
        return client;
    }
    public Observable<MealResponse> makeNetworkCall(){
        return  mealService.getCategories()
                .subscribeOn(Schedulers.io());
//        Gson gson=new GsonBuilder().setLenient().create();
//        Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//       MealService mealService=retrofit.create(MealService.class);
//        Call<MealResponse> call=mealService.getCategories();
//
//        call.enqueue(new Callback<MealResponse>() {
//            @Override
//            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                if(response.isSuccessful()){
//                    networkCallback.onSuccessResult(response.body().getCategories());
//                    Log.i(TAG, "onResponse: "+response.body());
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<MealResponse> call, Throwable t) {
//                networkCallback.onFailureResult(t.getMessage());
//                Log.i(TAG, "onFailure: "+t.getMessage());
//
//
//            }
//        });

    }
     public  Observable<MealResponse> randomNetworkCall(){
         return  mealService.getMeal()
                 .subscribeOn(Schedulers.io());
//         Gson gson=new GsonBuilder().setLenient().create();
//         Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//         MealService mealService=retrofit.create(MealService.class);
//         Call<MealResponse> call=mealService.getMeal();
//         call.enqueue(new Callback<MealResponse>(){
//
//             @Override
//             public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                      if(response.isSuccessful()){
//                         List<Meal> meals = response.body().getMeal();
//                         // Log response data
//                         if (meals != null) {
//                             Log.d(TAG, "Random Meals:");
//                             for (Meal meal : meals) {
//                                 Log.d(TAG, meal.toString());
//                             }
//                         } else {
//                             Log.d(TAG, "Random Meals: null");
//                         }
//                         // Now you can pass the randomMeals to the callback
//                         networkCallback.onSuccessResult(meals);
//                     } else {
//                         Log.e(TAG, "Response not successful: " + response.code());
//                     }
//                 }
//                 @Override
//             public void onFailure(Call<MealResponse> call, Throwable t) {
//                 networkCallback.onFailureResult(t.getMessage());
//                 Log.i(TAG, "onFailure Random Mail: "+t.getMessage());
//
//             }
//         });

     }
     public  Observable<MealResponse> mealByCatNetworkCall(String category){
         return  mealService.getMealByCategory(category)
                 .subscribeOn(Schedulers.io());
//         Gson gson=new GsonBuilder().setLenient().create();
//         Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//         MealService mealService=retrofit.create(MealService.class);
//         Call<MealResponse> call=mealService.getMealByCategory(category);
//         call.enqueue(new Callback<MealResponse>(){
//
//             @Override
//             public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                 if(response.isSuccessful()){
//                     List<Meal> meals = response.body().getMeal();
//                     // Log response data
//                     if (meals != null) {
//                         Log.d(TAG, " Meals By category:");
//                         for (Meal meal : meals) {
//                             Log.d(TAG, meal.toString());
//                         }
//                     } else {
//                         Log.d(TAG, " Meals: null");
//                     }
//                     // Now you can pass the randomMeals to the callback
//                     networkCallback.onSuccessResult(meals);
//                 } else {
//                     Log.e(TAG, "Response not successful: " + response.code());
//                 }
//             }
//
//             @Override
//             public void onFailure(Call<MealResponse> call, Throwable t) {
//                 networkCallback.onFailureResult(t.getMessage());
//                 Log.i(TAG, "onFailure Random Mail: "+t.getMessage());
//
//             }
//         });
     }

     public  Observable<MealResponse>  mealByIdNetworkCall(String idMeal) {
         return  mealService.getMealById(idMeal)
                 .subscribeOn(Schedulers.io());
//         Gson gson = new GsonBuilder().setLenient().create();
//         Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//         MealService mealService = retrofit.create(MealService.class);
//         Call<MealResponse> call = mealService.getMealById(idMeal);
//         call.enqueue(new Callback<MealResponse>() {
//
//             @Override
//             public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
//                 if (response.isSuccessful()) {
//                     List<Meal> meals = response.body().getMeal();
//                     // Log response data
//                     if (meals != null) {
//                         Log.d(TAG, " Meals By id:");
//                         for (Meal meal : meals) {
//                             Log.d(TAG, meal.toString());
//                         }
//                     } else {
//                         Log.d(TAG, " Meals: null");
//                     }
//                     // Now you can pass the randomMeals to the callback
//                     networkCallback.onSuccessResult(meals);
//                 } else {
//                     Log.e(TAG, "Response not successful: " + response.code());
//                 }
//             }
//
//             @Override
//             public void onFailure(Call<MealResponse> call, Throwable t) {
//                 networkCallback.onFailureResult(t.getMessage());
//                 Log.i(TAG, "onFailure Random Mail: " + t.getMessage());
//             }
//         });
     }
}


