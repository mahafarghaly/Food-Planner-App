package com.example.foodplanner.homefrag.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.RandomMeal;

public class DetailActivity extends AppCompatActivity {
    TextView tv_category,tv_country,tv_instructions,tv_meal_name;
    ImageView mealImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        RandomMeal meal = (RandomMeal) intent.getSerializableExtra("meal");

        tv_meal_name = findViewById(R.id.tv_meal_name);
        mealImage=findViewById(R.id.iv_meal);
        tv_category=findViewById(R.id.tv_category);
        tv_country=findViewById(R.id.tv_country);
        tv_instructions=findViewById(R.id.tv_instructions);
        //******************************************//
        tv_meal_name.setText(meal.getStrMeal());
        ImageView imageViewMeal = findViewById(R.id.iv_meal);
        Glide.with(this).load(meal.getStrMealThumb()).placeholder(R.drawable.ic_launcher_foreground).into(imageViewMeal);
        tv_category.setText(meal.getStrCategory());
        tv_country.setText(meal.getStrArea());
        tv_instructions.setText(meal.getStrInstructions());
    }
}