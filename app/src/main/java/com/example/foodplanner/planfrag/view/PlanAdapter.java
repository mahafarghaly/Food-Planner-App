package com.example.foodplanner.planfrag.view;

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
import com.example.foodplanner.favmeals.view.FavoriteAdapter;
import com.example.foodplanner.favmeals.view.OnFavoriteClickListener;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealPlan;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    public static final String TAG="PlanAdapter";
    private Context context;
    private OnPlanClickListener listener;
    private List<MealPlan> mealList;



    public PlanAdapter(Context context, ArrayList<MealPlan> productsList, OnPlanClickListener _listener) {
        this.mealList = productsList;
        this.context=context;
        this.listener=_listener;
        productsList=new ArrayList<MealPlan>();
    }
    public void setList(List<MealPlan> updateProducts){
        this.mealList=updateProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plan, parent, false);
        return new PlanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {
        MealPlan currentmeal = mealList.get(position);

        holder.titleTextView.setText(currentmeal.getMeal().getStrMeal());
      holder.timeText.setText(currentmeal.getDate());
        Glide.with(context).load(currentmeal.getMeal().getStrMealThumb()).into(holder.imageView);
        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlanMealClick(currentmeal);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("meal", currentmeal.getMeal());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, timeText;
        public ImageView imageView;
        public Button btn_remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_plan_name);
            timeText=itemView.findViewById(R.id.tv_date);
            imageView = itemView.findViewById(R.id.iv_plan);
            btn_remove=itemView.findViewById(R.id.btn_remove_plan);

        }
    }

}
