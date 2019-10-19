
package com.moringaschool.recipestore.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("meals")
    @Expose
    private Meals meals;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Store() {
    }

    /**
     * 
     * @param meals
     */
    public Store(Meals meals) {
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
