package com.example.foodplanner.favmeals.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.detail.view.DetailActivity;
import com.example.foodplanner.favmeals.presenter.FavPresenter;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    public static final String TAG="FavoriteAdapter";
    private Context context;
    private OnFavoriteClickListener listener;
    private List<Meal> mealList;



    public FavoriteAdapter(Context context, ArrayList<Meal> productsList, OnFavoriteClickListener _listener) {
        this.mealList = productsList;
        this.context=context;
        this.listener=_listener;
        productsList=new ArrayList<Meal>();
    }
    public void setList(List<Meal> updateProducts){
        this.mealList=updateProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal currentmeal = mealList.get(position);

        holder.titleTextView.setText(currentmeal.getStrMeal());

        Glide.with(context).load(currentmeal.getStrMealThumb()).into(holder.imageView);
        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFavoriteMealClick(currentmeal);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event
                // Open the meal details fragment
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("meal", currentmeal);
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
        public ImageButton btn_remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_favorite_name);
            imageView = itemView.findViewById(R.id.iv_favorite);
            btn_remove=itemView.findViewById(R.id.btn_remove);

        }
    }

}
