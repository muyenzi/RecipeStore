package com.moringaschool.recipestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FoodActivity extends AppCompatActivity {
    private TextView mSearchTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mSearchTextView = (TextView) findViewById(R.id.searchTextView);
        Intent intent=getIntent();
        String food=intent.getStringExtra("food");
        mSearchTextView.setText("Cooking Recipe For " + food);

    }
}
