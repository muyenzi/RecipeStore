
package com.moringaschool.recipestore.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoriesStore {

    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CategoriesStore() {
    }

    /**
     * 
     * @param categories
     */
    public CategoriesStore(List<Category> categories) {
        super();
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
