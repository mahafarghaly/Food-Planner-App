package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM Meal_table")
    Flowable<List<Meal>> getAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable insert(Meal meal);

    @Delete
    void delete(Meal meal);
}

