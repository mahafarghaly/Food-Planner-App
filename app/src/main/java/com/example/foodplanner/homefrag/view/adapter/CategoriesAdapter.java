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
import com.example.foodplanner.mealbycategory.view.MealByCategoryActivity;
import com.example.foodplanner.model.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter <CategoriesAdapter.ViewHolder>{
    public static final String TAG="AllProductsAdapter";
    private Context context;

    private List<Categories> categoriesList;

    public List<Categories> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
        notifyDataSetChanged();
    }

    public CategoriesAdapter(Context context, List<Categories> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
        categoriesList=new ArrayList<Categories>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categories category = categoriesList.get(position);

        holder.titleTextView.setText(category.getStrCategory());
        //holder.priceTextView.setText("Price: " + (category.getPrice()));
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = v.getContext();
                Intent intent = new Intent(context, MealByCategoryActivity.class);
                intent.putExtra("category", category);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, priceTextView;
        public ImageView imageView;
       // public Button btn_add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_category_name);
           // priceTextView = itemView.findViewById(R.id.tv_product_price);
            imageView = itemView.findViewById(R.id.iv_category);
           // btn_add=itemView.findViewById(R.id.btn_add);

        }
    }
}
