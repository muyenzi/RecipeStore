
package com.moringaschool.recipestore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipesStore {

    @SerializedName("meals")
    @Expose
    private Meals meals;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RecipesStore() {
    }

    /**
     * 
     * @param meals
     */
    public RecipesStore(Meals meals) {
        super();
        this.meals = meals;
    }

    public Meals getMeals() {
        return meals;
    }

    public void setMeals(Meals meals) {
        this.meals = meals;
    }

}
