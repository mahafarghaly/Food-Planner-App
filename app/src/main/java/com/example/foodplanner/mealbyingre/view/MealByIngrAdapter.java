package com.example.foodplanner.mealbyingre.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.detail.view.DetailActivity;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealByIngrAdapter extends RecyclerView.Adapter <MealByIngrAdapter.ViewHolder>{
    public static final String TAG="MealByIngrAdapter";
    private Context context;

    private List<Meal> mealList;
    OnMealByIngreClickListener listener;

    public List<Meal> getRandomMealList() {
        return mealList;
    }

    public void setRandomMealList(List<Meal> mealList) {
        this.mealList = mealList;
        notifyDataSetChanged();
    }

    public MealByIngrAdapter(Context context, List<Meal> meals,OnMealByIngreClickListener _listener) {
        this.context = context;
        this.mealList = meals;
        this.listener=_listener;
        meals =new ArrayList<Meal>();
    }


    @NonNull
    @Override
    public MealByIngrAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MealByIngrAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealByIngrAdapter.ViewHolder holder, int position) {
        Meal meal = mealList.get(position);

        holder.titleTextView.setText(meal.getStrMeal());
        //holder.priceTextView.setText("Price: " + (category.getPrice()));
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("meal", meal);
                context.startActivity(intent);

//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("mealId", meal.getStrMeal()); // Assuming getId() returns meal ID
//                context.startActivity(intent);
            }
        });
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onMealByIngredientClick(meal);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, priceTextView;
        public ImageView imageView;
        public ImageButton btn_add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_meal_name);
            // priceTextView = itemView.findViewById(R.id.tv_product_price);
            imageView = itemView.findViewById(R.id.iv_meal);
            btn_add=itemView.findViewById(R.id.btn_add_cm);

        }
    }
}
