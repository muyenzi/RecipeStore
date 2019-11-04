package com.moringaschool.recipestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesActivity extends AppCompatActivity {
    public static final String TAG = RecipesActivity.class.getSimpleName();

    private DatabaseReference mSearchedFoodReference;
    @BindView(R.id.search) Button mSearch;
    @BindView(R.id.searchEditText) EditText mSearchEditText;
    @BindView(R.id.image1) ImageView mImage1;
    @BindView(R.id.image2) ImageView mImage2;
    @BindView(R.id.image3) ImageView mImage3;
    @BindView(R.id.image4) ImageView mImage4;
    @BindView(R.id.image5) ImageView mImage5;
    @BindView(R.id.image6) ImageView mImage6;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedFoodReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_FOOD);

        mSearchedFoodReference.addValueEventListener(new ValueEventListener() { //attach listener

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot foodSnapshot : dataSnapshot.getChildren()) {
                    String food = foodSnapshot.getValue().toString();
                    Log.d("food updated", "food: " + food); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        ButterKnife.bind(this);

        animation = AnimationUtils.loadAnimation(RecipesActivity.this,R.anim.bounce);
        mSearch.startAnimation(animation);

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String food = mSearchEditText.getText().toString();
                saveFoodToFirebase(food);
                Intent intent = new Intent(RecipesActivity.this, FoodActivity.class);
                intent.putExtra("food", food);
                startActivity(intent);
            }
        });

        mImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images1=new Intent(RecipesActivity.this , Images1Activity.class);
                startActivity(images1);
            }
        });

        mImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images2=new Intent(RecipesActivity.this , Image2Activity.class);
                startActivity(images2);
            }
        });

        mImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images3=new Intent(RecipesActivity.this , Images3Activity.class);
                startActivity(images3);
            }
        });

        mImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images4=new Intent(RecipesActivity.this , Images4Activity.class);
                startActivity(images4);
            }
        });

        mImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images5=new Intent(RecipesActivity.this , Images5Activity.class);
                startActivity(images5);
            }
        });

        mImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent images6=new Intent(RecipesActivity.this , Images6Activity.class);
                startActivity(images6);
            }
        });


    }


    public void saveFoodToFirebase(String food) {
        mSearchedFoodReference.push().setValue(food);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedFoodReference.removeEventListener(mSearchedFoodReferenceListener);
//    }

}

