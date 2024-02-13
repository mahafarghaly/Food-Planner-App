package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

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

    public void getAllCategories(NetworkCallback networkCallback){
        remoteDataSource.makeNetworkCall((networkCallback));
    }

    public void getRandomMeal(NetworkCallback networkCallback){
        remoteDataSource.randomNetworkCall((networkCallback));
    }
    public void getMealByCategory(NetworkCallback networkCallback ,String category){
        remoteDataSource.mealByCatNetworkCall(networkCallback,category);
    }
    public void getMealById(NetworkCallback networkCallback,String strMeal){
        remoteDataSource.mealByIdNetworkCall(networkCallback,strMeal);
    }


    public LiveData<List<Meal>> getStoredData(){
        return localDataSource.getStoredData();
    }
    public void insertMeal(Meal meal){
        localDataSource.insert(meal);
    }
    public void deleteMeal(Meal meal){
        localDataSource.delete(meal);
    }






}
