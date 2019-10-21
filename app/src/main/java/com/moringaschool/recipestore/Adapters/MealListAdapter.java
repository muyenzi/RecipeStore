package com.moringaschool.recipestore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.recipestore.R;
import com.moringaschool.recipestore.models.Meal;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.MealViewHolder> {
    private List<Meal> mMeals;
    private  Context mContext;

    public MealListAdapter(Context context, List<Meal> meals) {
        mContext = context;
        mMeals = meals;
    }

    @Override
    public MealListAdapter.MealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_food, parent, false);
        MealViewHolder viewHolder = new MealViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MealListAdapter.MealViewHolder holder, int position) {
        holder.bindMeal(mMeals.get(position));
    }

    @Override
    public int getItemCount() {
        return mMeals.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mealImageView) ImageView mMealImageView;
        @BindView(R.id.mealNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.instructTextView) TextView mInstructTextView;
        @BindView(R.id.linkTextView) TextView mLinkTextView;

        private Context mContext;

        public MealViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMeal(Meal meal) {
            Picasso.get().load(meal.getStrMealThumb()).into(mMealImageView);
            mNameTextView.setText(meal.getStrMeal());
            mCategoryTextView.setText(meal.getStrCategory());
            mInstructTextView.setText(meal.getStrInstructions());
            mLinkTextView.setText(meal.getStrYoutube());
        }
    }
}
