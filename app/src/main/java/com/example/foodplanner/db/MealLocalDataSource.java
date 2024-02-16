package com.example.foodplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealDAO;
    private MealPlanDAO mealPlanDAO;

    private Flowable<List<Meal>> storedMeal;
    private Flowable<List<MealPlan>> storedPlan;
    private  static MealLocalDataSource repository=null;

    private MealLocalDataSource(Context _context){
        this.context= _context;
        AppDatabase db= AppDatabase.getInstance(context.getApplicationContext());
        mealDAO=db. mealDAO();
        storedMeal=mealDAO.getAllMeals();
        //plan
        mealPlanDAO=db.mealPlanDAO();
        storedPlan=mealPlanDAO.getAllMealPlanItems();


    }
    public static MealLocalDataSource getInstance(Context _context){
        if(repository==null){
            repository=new MealLocalDataSource (_context);
        }
        return  repository;
    }
    public Flowable<List<Meal>> getStoredData(){

        return storedMeal;
    }

    public void delete(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.delete(meal);
            }
        }).start();
    }

    public Completable insert(Meal meal){
        return mealDAO.insert(meal);
    }

    // functions for Plan DB
    public Flowable<List<MealPlan>> getStoredPlan(){

        return storedPlan;
    }
    public Completable insertPlan(MealPlan mealPlan){
        return mealPlanDAO.insertPlan(mealPlan);
    }
    public void deletePlan(MealPlan mealPlan){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealPlanDAO.delete(mealPlan);
            }
        }).start();
    }

}
