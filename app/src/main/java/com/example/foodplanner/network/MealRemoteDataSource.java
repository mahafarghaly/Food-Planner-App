package com.example.foodplanner.network;

import com.example.foodplanner.model.MealResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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

    }
     public  Observable<MealResponse> randomNetworkCall(){
         return  mealService.getMeal()
                 .subscribeOn(Schedulers.io());


     }
     public  Observable<MealResponse> mealByCatNetworkCall(String category){
         return  mealService.getMealByCategory(category)
                 .subscribeOn(Schedulers.io());

     }

     public  Observable<MealResponse>  mealByIdNetworkCall(String idMeal) {
         return  mealService.getMealById(idMeal)
                 .subscribeOn(Schedulers.io());

     }
    public  Observable<MealResponse>  areNetworkCall(){
        return mealService.getAllCountries()
                .subscribeOn(Schedulers.io());
    }
    public  Observable<MealResponse> mealByAreaNetworkCall(String area){
        return mealService.getMealByCountry(area)
                .subscribeOn(Schedulers.io());
    }
    public  Observable<MealResponse>  ingredientNetworkCall(){
        return mealService.getAllIngredient()
                .subscribeOn(Schedulers.io());
    }
    public  Observable<MealResponse> mealByIngredientNetworkCall(String ingredient){
        return mealService.getMealByIngredient(ingredient)
                .subscribeOn(Schedulers.io());
    }

}


