package com.example.foodplanner.db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.model.Meal;

import java.util.List;

public class MealLocalDataSource {
    private Context context;
    private MealDAO mealDAO;
    private LiveData<List<Meal>> storedMeal;
    private  static MealLocalDataSource repository=null;

    private MealLocalDataSource(Context _context){
        this.context= _context;
        AppDatabase db= AppDatabase.getInstance(context.getApplicationContext());
        mealDAO=db. mealDAO();
        storedMeal=mealDAO.getAllMeals();

//        this.productList=new ArrayList<>();
//      productList.add(new Product(1L,"1","test","Test",4.5,234,"https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"));
    }
    public static MealLocalDataSource getInstance(Context _context){
        if(repository==null){
            repository=new MealLocalDataSource (_context);
        }
        return  repository;
    }
    public LiveData<List<Meal>> getStoredData(){
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

    public void insert(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insert(meal);
            }
        }).start();
    }

}
