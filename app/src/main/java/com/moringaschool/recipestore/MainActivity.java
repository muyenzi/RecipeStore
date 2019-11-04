package com.moringaschool.recipestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.service.autofill.Validator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

//import butterknife.Bind;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.recipestore.Adapters.MealListAdapter;
import com.moringaschool.recipestore.ui.LoginActivity;
import com.moringaschool.recipestore.ui.UploadRecipeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @BindView(R.id.recipes) Button mRecipes;
    @BindView(R.id.uploadrecipes) Button mUpload;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animations);
        mRecipes.startAnimation(anim);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    getSupportActionBar().setTitle("Welcome, " + user.getDisplayName() + "!");
                }else {

                }
            }
        };

        mRecipes.setOnClickListener(this);
        mUpload.setOnClickListener(this);

    }
            @Override
            public void onClick(View v) {
                if (v == mRecipes) {


//                Toast.makeText(MainActivity.this, "Search!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, RecipesActivity.class);
                    startActivity(intent);
                }

                if (v == mUpload){
                    Intent intent=new Intent(MainActivity.this , UploadRecipeActivity.class);
                    startActivity(intent);
                }
            }



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.userLogout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

}

