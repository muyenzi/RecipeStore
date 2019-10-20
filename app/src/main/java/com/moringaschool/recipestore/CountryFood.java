package com.moringaschool.recipestore;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CountryFood  extends ArrayAdapter {

    private Context mContext;
    private String[] mRecipes;
    private String[] mIngredient;
    private String[] mIngredient1;
    private String[] mIngredient2;
    private String[] mIngredient3;
    private String[] mCategory;
    private String[] mInstruct;


    public CountryFood(Context mContext,int resource,String[] mRecipes,String[] mIngredient,String[] mIngredient1,String[] mIngredient2,String[] mIngredient3,String[] mCategory,String[] mInstruct){
        super(mContext ,resource);
        this.mContext=mContext;
        this.mRecipes=mRecipes;
        this.mIngredient=mIngredient;
        this.mIngredient1=mIngredient1;
        this.mIngredient2=mIngredient2;
        this.mIngredient3=mIngredient3;
        this.mCategory=mCategory;
        this.mInstruct=mInstruct;
    }

    @Override
    public Object getItem(int position) {
        String recipes = mRecipes[position];
        String ingredient=mIngredient[position];
        String ingredient1=mIngredient1[position];
        String ingredient2=mIngredient2[position];
        String ingredient3=mIngredient3[position];
        String category=mCategory[position];
        String instruct =mInstruct[position];

        return String.format(" Meal : %s \n Ingredients:\n %s,%s,%s,%s \n Category: %s \n Instructions : %s", recipes, ingredient ,ingredient1 ,ingredient2,ingredient3 ,category,instruct);
    }

    @Override
    public int getCount() {
        return mRecipes.length;
    }
}

