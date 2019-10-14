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

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodActivity extends AppCompatActivity {


    @BindView(R.id.searchTextView) TextView mSearchTextView;
    @BindView(R.id.foodList) ListView mFoodList;

    private String[] recipes=new String[] {"Italian","Chinese","Mexican","Taiwan","Nicaragua","France","Georgia","Ethiopian",
                                             "Armenia","Romania" ,"Japan","Korean" ,"Canadian" ,"Jamaican" ,"Portuguese" ," Austrian"};

    private String[] accompagne=new String[] {"fish ", "Mansaf, lamb cooked in yogurt with rice","pizza" ,"Organic Montenegrinbeef liver","Nicaraguan beef heart",
                                              "beefy soups of Burma" ,"Roasted Snails","shrimps with veggies and rice" ,"lettuce, dried tomatoes, Greek cheese","meat dumplings with Khvanchkara",
                                               " Caucasus kebab ","fish fillet", "beans with lots of pork meats ","armadillos with pineaple","garbanzo beans, olive oil and eggs"};

    private String ingredients="Directions\n" +
            "Preheat the oven to 325.\n" +
            "Beat the butter, sugar, eggs and vanilla together until creamy.\n" +
            "Mix together the flour, baking soda and salt in a separate bowl.\n" +
            "Add flour mixture to butter mixture slowly.\n" +
            "Stir in chocolate chips, oatmeal and coconut.\n" +
            "Bake for 10 minutes or until golden brown.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        ButterKnife.bind(this);

        CountryFood adapter= new CountryFood(this ,android.R.layout.simple_list_item_1 ,recipes , accompagne);
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
        mSearchTextView.setText("Cooking Recipe For " + food );

    }
}
