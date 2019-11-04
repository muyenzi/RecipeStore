package com.moringaschool.recipestore.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.moringaschool.recipestore.R;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class UploadRecipeActivity extends AppCompatActivity {
private ImageView uploadImage;
private EditText  uploadName;
private Button   uploadRecipes;
private ProgressDialog mProgressDialog;

private final static int mWidth = 512;
    private final static int mLength = 512;

    private ArrayList<String> pathArray;
    private int array;

    private StorageReference mStorageRef;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_recipe);

        uploadImage=(ImageView)findViewById(R.id.uploadImage);
        uploadName=(EditText) findViewById(R.id.uploadName);
        uploadRecipes=(Button) findViewById(R.id.uploadrecipes);
        pathArray=new ArrayList<>();
        mProgressDialog=new ProgressDialog(UploadRecipeActivity.this);
        auth=FirebaseAuth.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference();
    }
}
