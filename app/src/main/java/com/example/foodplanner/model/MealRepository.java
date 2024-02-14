package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.network.MealRemoteDataSource;


import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class MealRepository {
    MealRemoteDataSource remoteDataSource;
    MealLocalDataSource localDataSource;
    private static MealRepository repo=null;
    public static MealRepository getInstance(MealRemoteDataSource remoteDataSource,MealLocalDataSource localDataSource){
        if(repo==null){
            repo=new MealRepository(remoteDataSource ,localDataSource);
        }
        return repo;
    }
    private MealRepository(MealRemoteDataSource remoteDataSource ,MealLocalDataSource localDataSource){
        this.remoteDataSource=remoteDataSource;
        this.localDataSource=localDataSource;
    }

    public Observable<MealResponse> getAllCategories( ){
        return remoteDataSource.makeNetworkCall();
    }

    public Observable<MealResponse> getRandomMeal(){
        return remoteDataSource.randomNetworkCall();
    }
    public Observable<MealResponse> getMealByCategory(String category){
       return  remoteDataSource.mealByCatNetworkCall(category);
    }
    public Observable<MealResponse> getMealById(String strMeal){
        return remoteDataSource.mealByIdNetworkCall(strMeal);
    }


    public Flowable<List<Meal>> getStoredData(){
        return localDataSource.getStoredData();
    }
    public Completable insertMeal(Meal meal){
        return localDataSource.insert(meal);
    }
    public void deleteMeal(Meal meal){
        localDataSource.delete(meal);
    }






}
