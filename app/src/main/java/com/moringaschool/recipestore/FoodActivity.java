package com.moringaschool.recipestore;


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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;

public class FoodActivity extends AppCompatActivity {


    @BindView(R.id.searchTextView) TextView mSearchTextView;



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
                if (response.isSuccessful()) {


                }
            }

            @Override
            public void onFailure(Call<Store> call, Throwable t) {

            }

        });



    }
}
