package com.example.foodplanner.mealbyingre.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.mealbycategory.presenter.MealByCategoryPresenter;
import com.example.foodplanner.mealbycategory.presenter.MealByCategoryPresenterImpl;
import com.example.foodplanner.mealbycategory.view.MealByCategoryAdapter;
import com.example.foodplanner.mealbyingre.presenter.MealByIngrePresenter;
import com.example.foodplanner.mealbyingre.presenter.MealByIngrePresenterImpl;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MealByIngreActivity extends AppCompatActivity implements MealByIngreView,OnMealByIngreClickListener{
    TextView tv_ingredient;//,tv_country,tv_instructions,tv_meal_name;//
    ImageView ingredientImage;
    ImageButton btn_back;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    MealByIngrAdapter mealByIngrAdapter;
    MealByIngrePresenter mealByIngrePresenter;
    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_by_ingre);
        tv_ingredient = findViewById(R.id.ingre_name);
       ingredientImage =findViewById(R.id.image_ingre);
        recyclerView=findViewById(R.id.rv_meal_ingre);
        btn_back=findViewById(R.id.btn_back_ing);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        Intent intent = getIntent();
        Meal meal = (Meal) intent.getSerializableExtra("meal");
        tv_ingredient.setText(meal.getStrIngredient());
        String imgURL="https://www.themealdb.com/images/ingredients/"+
              meal.getStrIngredient()+".png";
        Glide.with(this).load(imgURL)
                .into(ingredientImage);
        //
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);// Use getContext() to get the context of the fragment
        mealByIngrAdapter = new MealByIngrAdapter(this, new ArrayList<>(),this);
        mealByIngrePresenter = new MealByIngrePresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(this)
                ));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(mealByIngrAdapter);
        //***============================//
        mealByIngrePresenter.getMealByIngredient(meal.getStrIngredient());

    }

    @Override
    public void showMealByIngredient(List<Meal> meals) {
        mealByIngrAdapter.setRandomMealList(meals);
        mealByIngrAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An Error Occurred");
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void addMeal(Meal meal) {
        mealByIngrePresenter.addToFav(meal);
    }

    @Override
    public void onMealByIngredientClick(Meal meal) {
        if(currentUser!=null) {
            addMeal(meal);
            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show();
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Please register first.");
            builder.setTitle("Not available for guests!");
            AlertDialog dialog=builder.create();
            dialog.show();
        }
    }

}