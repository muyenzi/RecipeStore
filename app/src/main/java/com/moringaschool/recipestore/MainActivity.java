package com.moringaschool.recipestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recipes) Button mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   ButterKnife.bind(this);

        mRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Search!", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, RecipesActivity.class);
                startActivity(intent);
            }
        });
    }
}
