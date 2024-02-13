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

@Dao
public interface MealDAO {
    @Query("SELECT * FROM Meal_table")
    LiveData<List<Meal>> getAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Meal meal);

    @Delete
    void delete(Meal meal);
}

