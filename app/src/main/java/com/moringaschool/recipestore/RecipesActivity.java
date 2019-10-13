package com.moringaschool.recipestore;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrinterId;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity {
    public static final String TAG = RecipesActivity.class.getSimpleName();

    @BindView(R.id.search)
    Button mSearch;
    @BindView(R.id.searchEditText)
    EditText mSearchEditText;
    @BindView(R.id.image1)
    ImageView mImage1;
    @BindView(R.id.image2)
    ImageView mImage2;
    @BindView(R.id.image3)
    ImageView mImage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        ButterKnife.bind(this);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String food = mSearchEditText.getText().toString();
                Log.d(TAG, food);
                Intent intent = new Intent(RecipesActivity.this, FoodActivity.class);
                intent.putExtra("food", food);
                startActivity(intent);
            }
        });

        mImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images1=new Intent(RecipesActivity.this , MenuActivity.class);
                startActivity(images1);
            }
        });

        mImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images2=new Intent(RecipesActivity.this , MenuActivity.class);
                startActivity(images2);
            }
        });

        mImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images3=new Intent(RecipesActivity.this , MenuActivity.class);
                startActivity(images3);
            }
        });



    }
}

