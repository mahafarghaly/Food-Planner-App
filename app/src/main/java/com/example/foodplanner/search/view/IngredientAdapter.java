package com.example.foodplanner.search.view;

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
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.homefrag.view.adapter.CountryAdapter;
import com.example.foodplanner.mealbycountry.view.MealByCountryActivity;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter  extends RecyclerView.Adapter <IngredientAdapter.ViewHolder>{
    public static final String TAG="IngredientAdapter";
    private Context context;

    private List<Meal> ingredientList;

    //OnMealClickListener listener;
    public List<Meal> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Meal> mealList) {
        this.ingredientList = mealList;
        notifyDataSetChanged();
    }

    public IngredientAdapter(Context context, List<Meal> meals) {
        this.context = context;
        this.ingredientList = meals;
        meals =new ArrayList<Meal>();
        // this.listener=_listener;
    }


    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new IngredientAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        Meal meal = ingredientList.get(position);

        holder.titleTextView.setText(meal.getStrArea()

        );

        Glide.with(context).load("https://flagsapi.com/" +getCountryCode(meal.getStrArea()) + "/flat/64.png")
                .apply(new RequestOptions().override(120, 64))
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event
                // Open the meal details fragment
                Context context = v.getContext();
                Intent intent = new Intent(context, MealByCountryActivity.class);
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
            titleTextView = itemView.findViewById(R.id.tv_country_name);
            imageView = itemView.findViewById(R.id.iv_country);


        }
    }
    public static String getCountryCode(String countryName) {
        switch (countryName) {
            case "American":
                return "AS";
            case "British":
                return "GB";
            case "Canadian":
                return "CA";
            case "Chinese":
                return "CN";
            case "Croatian":
                return "HR";
            case "Dutch": //not found
                return "NL";
            case "Egyptian":
                return "EG";
            case "Filipino":
                return "PH";
            case "French":
                return "PF";
            case "Greek":
                return "GR";
            case "Indian":
                return "IN";
            case "Irish":
                return "IE";
            case "Italian":
                return "IT";
            case "Jamaican":
                return "JM";
            case "Japanese":
                return "JP";
            case "Kenyan":
                return "KE";
            case "Malaysian":
                return "MY";
            case "Mexican":
                return "MX";
            case "Polish":
                return "PL";
            case "Portuguese":
                return "PT";
            case "Russian":
                return "RU";
            case "Moroccan":
                return "MA";

            case "Spanish":
                return "ES";
            case "Thai":
                return "TH";
            case "Tunisian":
                return "TN";
            case "Turkish":
                return "TC";
            case "Vietnamese":
                return "VN";
            case "Unknown":
                return " ";


            default:
                return "";
        }
    }
}

