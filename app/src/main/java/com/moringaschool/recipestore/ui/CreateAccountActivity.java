package com.moringaschool.recipestore.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringaschool.recipestore.MainActivity;
import com.moringaschool.recipestore.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mDialog;
    private String mUserNameEntered;

    @BindView(R.id.addUserButton) Button mAddUserButton;
    @BindView(R.id.userName) EditText mUserName;
    @BindView(R.id.userEmail) EditText mUserEmail;
    @BindView(R.id.userPass) EditText mUserPass;
    @BindView(R.id.confirmUserPass) EditText mConfirmUserPass;
    @BindView(R.id.userLogin) TextView mUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        createSignupDialog();
        createAuthStateListener();
        mAuth = FirebaseAuth.getInstance();
        mUserLogin.setOnClickListener(this);
        mAddUserButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == mUserLogin) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mAddUserButton) {
            addUser();
        }
    }

    private void createSignupDialog(){
        mDialog=new ProgressDialog(this);
        mDialog.setTitle("Loading....");
        mDialog.setMessage("Checking Authentication...");
        mDialog.setCancelable(false);
    }

        private void addUser() {
            final String mUserNameEntered = mUserName.getText().toString().trim();
            final String userEmail = mUserEmail.getText().toString().trim();
            String userPass = mUserPass.getText().toString().trim();
            String confirmUserPass = mConfirmUserPass.getText().toString().trim();

            boolean acceptedEmail= acceptedEmail(userEmail);
            boolean acceptedPass= acceptedPassword(userPass , confirmUserPass);
            boolean acceptedName= acceptedName(mUserNameEntered);

            if (!acceptedEmail || !acceptedName || !acceptedPass) return;
            mDialog.show();

            mAuth.createUserWithEmailAndPassword(userEmail, userPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mDialog.dismiss();

                            if (task.isSuccessful()) {
                                Log.d(TAG, "Authentication successful");
                                createFirebaseUserProfile(task.getResult().getUser());
                            } else {
                                Toast.makeText(CreateAccountActivity.this, "Invalid Email or Password.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    private void createAuthStateListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser chef = firebaseAuth.getCurrentUser();
                if (chef != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean acceptedEmail(String userEmail) {
        boolean goodEmail =
                (userEmail != null && android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches());
        if (!goodEmail) {
            mUserEmail.setError("Invalid email address");
            return false;
        }
        return goodEmail;
    }

    private boolean acceptedName(String mUserNameEntered) {
        if (mUserNameEntered.equals("")) {
            mUserName.setError("Please enter your name");
            return false;
        }
        return true;
    }

    private boolean acceptedPassword(String userPass, String confirmUserPass) {
        if (userPass.length() < 7) {
            mUserPass.setError("Strong password required");
            return false;
        } else if (!userPass.equals(confirmUserPass)) {
            mConfirmUserPass.setError("Passwords do not match");
            return false;
        }
        return true;
    }
    private void createFirebaseUserProfile(final FirebaseUser chef){
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mUserNameEntered)
                .build();

        chef.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, chef.getDisplayName());
                            Toast.makeText(CreateAccountActivity.this, "The User Name", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}