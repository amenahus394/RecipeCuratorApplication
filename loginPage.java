package com.example.collegeapp_amenahussain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class loginPage extends AppCompatActivity implements View.OnClickListener {

    ImageView loginlogo;
    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        loginlogo = findViewById(R.id.image_logo);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        signIn = findViewById(R.id.signinbutton);
        signIn.setOnClickListener(this);

        editTextEmail = findViewById(R.id.ea_edittext);
        editTextPassword = findViewById(R.id.ps_edittext);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        loginlogo.setImageResource(R.drawable.flashfitlogo);

        forgotPassword = findViewById(R.id.forgotpass_txt);
        forgotPassword.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(this, registerUser.class));
                break;
            case R.id.signinbutton:
                userLogin();
                break;

            case R.id.forgotpass_txt:
                startActivity(new Intent(loginPage.this, forgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Valid email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editTextPassword.setError("Min password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        startActivity(new Intent(loginPage.this, MainActivity.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(loginPage.this, "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(loginPage.this, "Failed to login! Please check your credentials!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}