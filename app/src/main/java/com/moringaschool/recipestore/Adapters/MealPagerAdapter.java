package com.moringaschool.recipestore.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.moringaschool.recipestore.models.Meal;


import java.util.List;

public class MealPagerAdapter extends FragmentPagerAdapter {
    private List<Meal> mMeals;

    public MealPagerAdapter(FragmentManager fm,int behavior, List<Meal>meals){
        super(fm , behavior);
        mMeals = meals;
    }

    @Override
    public  int getCount(){
        return mMeals.size();
    }

    @Override
    public  CharSequence getPageTitle(int position){
        return  mMeals.get(position).getStrMeal();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }
}
