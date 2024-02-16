package com.example.foodplanner.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_plan")
public class MealPlan{
    @PrimaryKey(autoGenerate = true)
    private long id;

    private String date;
    private Meal meal;

    public MealPlan( String date, Meal meal) {
        this.date = date;
        this.meal = meal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
