package com.example.foodplanner.search.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.mealbycategory.view.MealByCategoryActivity;
import com.example.foodplanner.mealbycountry.view.MealByCountryActivity;
import com.example.foodplanner.mealbyingre.view.MealByIngreActivity;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

import kotlin.collections.FloatIterator;

public class IngredientAdapter  extends RecyclerView.Adapter <IngredientAdapter.ViewHolder>{
    public static final String TAG="IngredientAdapter";
    private Context context;

    private List<Meal> ingredientList;


    OnIngredientClickListener listener;
    public List<Meal> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Meal> mealList) {


        this.ingredientList= mealList;
        notifyDataSetChanged();
    }

    public IngredientAdapter(Context context, List<Meal> meals,OnIngredientClickListener _listener) {
        this.context = context;
        this.ingredientList = meals;
        meals =new ArrayList<Meal>();
         this.listener=_listener;
    }


    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ingredient, parent, false);
        return new IngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
       Meal meal = ingredientList.get(position);

holder.titleTextView.setText(meal.getStrIngredient());
        String imgURL="https://www.themealdb.com/images/ingredients/"+
                ingredientList.get(position).getStrIngredient()+".png";
        Glide.with(context).load(imgURL)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, MealByIngreActivity.class);
                intent.putExtra("meal", meal);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, priceTextView;
        public ImageView imageView;
        public ImageButton btn_add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_ingredient_name);
            imageView = itemView.findViewById(R.id.iv_ingredient);


        }
    }

}

