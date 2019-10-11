package com.moringaschool.recipestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {

    private TextView mSearchTextView;
    private ListView mFoodList;

    private String[] recipes=new String[] {"Italian","Chinese","Mexican","Taiwan","Nicaragua","France","Georgia","Ethiopian",
                                             "Armenia","Romania" ,"Japan","Korean" ,"Canadian" ,"Jamaican" ,"Portuguese" ," Austrian"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        mFoodList= (ListView) findViewById(R.id.foodList);
        mSearchTextView = (TextView) findViewById(R.id.searchTextView);

        ArrayAdapter adapter= new ArrayAdapter(this ,android.R.layout.simple_list_item_1 ,recipes);
        mFoodList.setAdapter(adapter);

        mFoodList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String recipe = ((TextView)view).getText().toString();
                Toast.makeText(FoodActivity.this, recipe, Toast.LENGTH_LONG).show();
            }
        });
        Intent intent=getIntent();
        String food=intent.getStringExtra("food");
        mSearchTextView.setText("Cooking Recipe For " + food);

    }
}
