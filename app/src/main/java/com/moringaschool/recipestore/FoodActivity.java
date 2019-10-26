package com.moringaschool.recipestore;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
//    private SharedPreferences mFoodPreferences;
//    private String mPreviousFood;

    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private MealListAdapter mAdapter;
    public List<Meal> meals;
    private Button mSaveMealButton;
    private Meal mMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);

        mSaveMealButton=(Button)findViewById(R.id.saveMealButton);

        Intent intent=getIntent();
        String food=intent.getStringExtra("food");

        getMeals(food);
//        mFoodPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mPreviousFood = mFoodPreferences.getString(Constants.PREFERENCES_FOOD_KEY, null);
//        if (mPreviousFood != null) {
//            getMeals(mPreviousFood);
//        }

        mSaveMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference mealRef = FirebaseDatabase
                        .getInstance()
                        .getReference(Constants.FIREBASE_CHILD_MEALS);
                mealRef.push().setValue(mMeals);
                 Toast.makeText(FoodActivity.this, "SAVED!", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void getMeals(String food) {
        final MealClient mealClient = new MealClient();

        MealApi client = MealClient.getClient();

        Call<Store> call = client.getMeals(food);

        call.enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Meal> mealList = response.body().getMeals();

                    meals = response.body().getMeals();
                    mAdapter = new MealListAdapter(FoodActivity.this, meals);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(FoodActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

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