package com.example.foodplanner.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealPlanDAO {

    @Query("SELECT * FROM meal_plan")
    Flowable<List<MealPlan>> getAllMealPlanItems();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insertPlan(MealPlan mealPlan);
    @Delete
    void delete(MealPlan mealPlan);
}
