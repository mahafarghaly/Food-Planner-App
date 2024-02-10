package com.example.foodplanner.homefrag.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.RandomMeal;

import java.util.ArrayList;
import java.util.List;

public class RandomAdapter extends RecyclerView.Adapter <RandomAdapter.ViewHolder>{
public static final String TAG="AllRandomAdapter";
private Context context;

private List<RandomMeal> randomMealList;

public List<RandomMeal> getRandomMealList() {
        return randomMealList;
        }

public void setRandomMealList(List<RandomMeal> randomMealList) {
        this.randomMealList = randomMealList;
        notifyDataSetChanged();
        }

public RandomAdapter(Context context, List<RandomMeal> randomMeals) {
        this.context = context;
        this.randomMealList = randomMeals;
    randomMeals=new ArrayList<RandomMeal>();
        }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_random, parent, false);
        return new ViewHolder(view);
    }

    @Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RandomMeal randomMeal = randomMealList.get(position);

        holder.titleTextView.setText(randomMeal.getStrMeal());
        //holder.priceTextView.setText("Price: " + (category.getPrice()));
        Glide.with(context).load(randomMeal.getStrMealThumb()).into(holder.imageView);
//        holder.btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //listener.onProductClick(currentProduct);
//            }
//        });

        }

@Override
public int getItemCount() {
        return randomMealList.size();
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
