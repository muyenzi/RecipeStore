package com.moringaschool.recipestore;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.moringaschool.recipestore.models.Meal;
import com.moringaschool.recipestore.models.Store;
import com.moringaschool.recipestore.network.MealApi;
import com.moringaschool.recipestore.network.MealClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

public class FoodActivity extends AppCompatActivity {

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.searchTextView) TextView mSearchTextView;
    @BindView(R.id.foodList) ListView mListView;

    public List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);

        Intent intent=getIntent();
        String food=intent.getStringExtra("food");
        mSearchTextView.setText("Cooking Recipe For " + food );

        MealApi client = MealClient.getClient();

        Call<Store> call = client.getMeals(food );

        call.enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Meal> mealList = response.body().getMeals();
                    String[] meals = new String[mealList.size()];


                    for (int i = 0; i < meals.length; i++){
                        meals[i] = mealList.get(i).getStrMeal();
                    }

                    ArrayAdapter adapter
                            = new CountryFood(FoodActivity.this, android.R.layout.simple_list_item_1, meals);
                    mListView.setAdapter(adapter);

                    showMeals();

                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<Store> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showMeals() {
        mListView.setVisibility(View.VISIBLE);
        mSearchTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}