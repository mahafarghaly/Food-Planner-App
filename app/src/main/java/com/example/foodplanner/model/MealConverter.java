package com.example.foodplanner.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class MealConverter {
    @TypeConverter
    public static Meal fromString(String value) {
        return new Gson().fromJson(value, Meal.class);
    }

    @TypeConverter
    public static String mealToString(Meal meal) {
        return new Gson().toJson(meal);
    }
}
