package com.example.foodplanner.homefrag.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class MealAdapter extends RecyclerView.Adapter <MealAdapter.ViewHolder>{
public static final String TAG="AllRandomAdapter";
private Context context;

private List<Meal> mealList;

public List<Meal> getRandomMealList() {
        return mealList;
        }

public void setRandomMealList(List<Meal> mealList) {
        this.mealList = mealList;
        notifyDataSetChanged();
        }

public MealAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.mealList = meals;
    meals =new ArrayList<Meal>();
        }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_random, parent, false);
        return new ViewHolder(view);
    }

    @Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal meal = mealList.get(position);

        holder.titleTextView.setText(meal.getStrMeal());
        //holder.priceTextView.setText("Price: " + (category.getPrice()));
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.imageView);
//        holder.btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //listener.onProductClick(currentProduct);
//            }
//        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event
                // Open the meal details fragment
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("meal", meal);
                context.startActivity(intent);
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
    // public Button btn_add;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.tv_random_name);
        // priceTextView = itemView.findViewById(R.id.tv_product_price);
        imageView = itemView.findViewById(R.id.iv_random);
        // btn_add=itemView.findViewById(R.id.btn_add);

    }
}
}
