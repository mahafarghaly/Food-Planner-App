package com.example.foodplanner.detail.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.detail.presenter.DetailActivityPresenterImpl;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;

import java.util.List;

public class DetailActivity extends AppCompatActivity implements DetailByIdView{
    TextView tv_category,tv_country,tv_instructions,tv_meal_name, tv_ingredient,tv_measure;
    ImageView mealImage;
    DetailActivityPresenterImpl presenter;
    private static final String TAG = "DetailActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Meal meal = (Meal) intent.getSerializableExtra("meal");
        //String mealId = getIntent().getStringExtra("mealId");
        presenter = new DetailActivityPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance()
                        // ProductLocalDataSource.getInstance(this)
                ));
        presenter.getMealById(meal.getIdMeal());

        // Call presenter method to fetch meal details by ID
     //  presenter.getMealById(mealId);
        /////////////////

        tv_meal_name = findViewById(R.id.tv_meal_name);
        mealImage=findViewById(R.id.iv_meal);
        tv_category=findViewById(R.id.tv_category);
        tv_country=findViewById(R.id.tv_country);
        tv_instructions=findViewById(R.id.tv_instructions);
        tv_measure=findViewById(R.id.tv_ingrdiant);
        tv_measure=findViewById(R.id.tv_messure);
        //******************************************//

    }



    @Override
    public void showErrMsg(String error) {
        Log.i(TAG, "showErrMsg: ");
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog=builder.create();
        dialog.show();
    }


    @Override
    public void showMealById(List<Meal> meals) {
        if (meals != null && !meals.isEmpty()) {
            Meal meal = meals.get(0); // Assuming you only expect one meal
            // Populate UI elements with meal details
            tv_meal_name.setText(meal.getStrMeal());
            Glide.with(this).load(meal.getStrMealThumb()).placeholder(R.drawable.ic_launcher_foreground).into(mealImage);
            tv_category.setText(meal.getStrCategory());
            tv_country.setText(meal.getStrArea());
            tv_instructions.setText(meal.getStrInstructions());
            //if(!meal.getStrIngredient1().isEmpty()){
//                tv_ingredient.setText(meal.getStrIngredient1());
//                tv_measure.setText(meal.getTrMeasure1());
               // ingredient.append("\n \u2022"+meal.getStrIngredient1());
           // }
        }
    }
//
//    @Override
//    public void showErrMsg(String error) {
//
//    }
}