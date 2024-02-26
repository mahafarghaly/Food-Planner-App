package com.example.foodplanner.mealbycountry.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.db.MealLocalDataSource;
import com.example.foodplanner.detail.view.DetailActivity;
import com.example.foodplanner.homefrag.view.HomeFragment;
import com.example.foodplanner.mealbycategory.presenter.MealByCategoryPresenter;
import com.example.foodplanner.mealbycategory.presenter.MealByCategoryPresenterImpl;
import com.example.foodplanner.mealbycategory.view.MealByCategoryAdapter;
import com.example.foodplanner.mealbycountry.presenter.MealByCountryPresenter;
import com.example.foodplanner.mealbycountry.presenter.MealByCountryPresenterImpl;
import com.example.foodplanner.model.Categories;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.MealRemoteDataSource;
import com.example.foodplanner.signup.SignUpActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MealByCountryActivity extends AppCompatActivity implements MealByCountryView,OnMealByAreaClickListener{
    TextView tv_country;//,tv_country,tv_instructions,tv_meal_name;//

    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    MealByCountryAdapter mealByCountryAdapter;
    MealByCountryPresenter mealByCountryPresenter;
    ImageView back_btn;
    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser currentUser = auth.getCurrentUser();
    private static final String TAG = "MealByCountryActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_by_country);
        tv_country=findViewById(R.id.tv_area_name);
        recyclerView=findViewById(R.id.rv_meal_country);
        back_btn=findViewById(R.id.btn_back);
        Intent intent = getIntent();
        Meal meal = (Meal) intent.getSerializableExtra("meal");
        tv_country.setText(meal.getStrArea());
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);// Use getContext() to get the context of the fragment
        mealByCountryAdapter = new MealByCountryAdapter(this, new ArrayList<>(),this);
        mealByCountryPresenter = new MealByCountryPresenterImpl(this,
                MealRepository.getInstance(MealRemoteDataSource.getInstance(),
                        MealLocalDataSource.getInstance(this)
                ));

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(mealByCountryAdapter);
        //***============================//
        mealByCountryPresenter.getMealByCountry(meal.getStrArea());
        //**********======================//
   back_btn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           onBackPressed();

       }
   });

    }

    @Override
    public void showMealByCountry(List<Meal> meals) {
        mealByCountryAdapter.setRandomMealList(meals);
        mealByCountryAdapter.notifyDataSetChanged();
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
    public void addMeal(Meal meal) {
        mealByCountryPresenter.addToFav(meal);
    }

    @Override
    public void onCountryClick(Meal meal) {
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