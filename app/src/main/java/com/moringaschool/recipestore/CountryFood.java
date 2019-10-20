package com.moringaschool.recipestore;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CountryFood  extends ArrayAdapter {

    private Context mContext;
    private String[] mRecipes;
    private String[] mAccompagne;

    public CountryFood(Context mContext, int resource , String[] mRecipes){
        super(mContext ,resource);
        this.mContext=mContext;
        this.mRecipes=mRecipes;
        this.mAccompagne=mAccompagne;
    }

    @Override
    public Object getItem(int position) {
        String recipes = mRecipes[position];
        String accompagne = mAccompagne[position];
        return String.format("%s Food  \n  %s", recipes, accompagne);
    }

    @Override
    public int getCount() {
        return mRecipes.length;
    }
}

