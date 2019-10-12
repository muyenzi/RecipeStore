package com.moringaschool.recipestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    private TextView mMenu1TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mMenu1TextView=(TextView)findViewById(R.id.menu1TextView);

        Intent images1=getIntent();

        mMenu1TextView.setText("\n*Ingredients\n\n" +
                "Butter ," +
                "White sugar ," +
                "Brown sugar ," +
                "Eggs ," +
                "Vanilla \n" +
                "Flour ," +
                "Baking soda ," +
                "Salt ," +
                "Chocolate chips ," +
                "Oatmeal" +
                "-Coconut \n\n"+
                "* Directions\n" +
                "1.Preheat the oven to 325.\n" +
                "2.Beat the butter, sugar, eggs and vanilla together until creamy.\n" +
                "3.Mix together the flour, baking soda and salt in a separate bowl.\n" +
                "4.Add flour mixture to butter mixture slowly.\n" +
                "5.Stir in chocolate chips, oatmeal and coconut.\n" +
                "6.Bake for 10 minutes or until golden brown.");


    }
}
