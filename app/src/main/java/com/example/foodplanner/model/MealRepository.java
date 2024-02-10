package com.example.foodplanner.model;

import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.network.NetworkCallback;

public class MealRepository {
    MealRemoteDataSource remoteDataSource;
    private static MealRepository repo=null;
    public static MealRepository getInstance(MealRemoteDataSource remoteDataSource/*,ProductLocalDataSource localDataSource*/){
        if(repo==null){
            repo=new MealRepository(remoteDataSource /*,localDataSource*/);
        }
        return repo;
    }
    private MealRepository(MealRemoteDataSource remoteDataSource/*ProductLocalDataSource localDataSource*/){
        this.remoteDataSource=remoteDataSource;
        //this.localDataSource=localDataSource;
    }

    public void getAllCategories(NetworkCallback networkCallback){
        remoteDataSource.makeNetworkCall((networkCallback));
    }

    public void getRandomMeal(NetworkCallback networkCallback){
        remoteDataSource.randomNetworkCall((networkCallback));
    }
}
