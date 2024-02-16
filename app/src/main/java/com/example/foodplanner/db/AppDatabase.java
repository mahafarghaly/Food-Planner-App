package com.example.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealConverter;
import com.example.foodplanner.model.MealPlan;
@TypeConverters({MealConverter.class})
@Database(entities = {Meal.class, MealPlan.class},version = 6)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance=null;
    public abstract MealDAO mealDAO();
    public abstract MealPlanDAO mealPlanDAO();
    public static synchronized  AppDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Mealsdb")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
