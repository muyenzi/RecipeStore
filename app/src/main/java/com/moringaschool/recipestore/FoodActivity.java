package com.moringaschool.recipestore;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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

import com.moringaschool.recipestore.Adapters.MealListAdapter;
import com.moringaschool.recipestore.models.Meal;
import com.moringaschool.recipestore.models.Store;
import com.moringaschool.recipestore.network.MealApi;
import com.moringaschool.recipestore.network.MealClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

public class FoodActivity extends AppCompatActivity {
    private static final String TAG = FoodActivity.class.getSimpleName();



    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private MealListAdapter mAdapter;

    public List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);


        Intent intent=getIntent();
        String food=intent.getStringExtra("food");


        MealApi client = MealClient.getClient();

        Call<Store> call = client.getMeals(food);

        call.enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Meal> mealList = response.body().getMeals();
//                    String[] meals = new String[mealList.size()];
//                    String[] ingred = new String [mealList.size()];
//                    String[] ingred1 =new String[mealList.size()];
//                    String[] ingred2 =new String[mealList.size()];
//                    String[] ingred3 =new String[mealList.size()];
//                    String[] category =new String[mealList.size()];
//                    String[] instruct =new String[mealList.size()];

                    meals = response.body().getMeals();
                    mAdapter = new MealListAdapter(FoodActivity.this, meals);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(FoodActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);


//                    for (int i = 0; i < meals.length; i++){
//                        meals[i] = mealList.get(i).getStrMeal();
//                    }
//
//                    for (int i = 0; i < ingred.length; i++){
//                        ingred[i] = mealList.get(i).getStrIngredient1();
//                    }
//
//                    for (int i = 0; i < ingred1.length; i++){
//                        ingred1[i] = mealList.get(i).getStrIngredient2();
//                    }
//
//                    for (int i = 0; i < ingred2.length; i++){
//                        ingred2[i] = mealList.get(i).getStrIngredient3();
//                    }
//
//                    for (int i = 0; i < ingred3.length; i++){
//                        ingred3[i] = mealList.get(i).getStrIngredient4();
//                    }
//
//                    for (int i = 0; i < category.length; i++){
//                        category[i] = mealList.get(i).getStrCategory();
//                    }
//
//                    for (int i = 0; i < instruct.length; i++){
//                        instruct[i] = mealList.get(i).getStrInstructions();
//                    }
//
//                    ArrayAdapter adapter
//                            = new CountryFood(FoodActivity.this, android.R.layout.simple_list_item_1, meals,ingred,ingred1,ingred2,ingred3,category,instruct);
//                    mListView.setAdapter(adapter);

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

        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}