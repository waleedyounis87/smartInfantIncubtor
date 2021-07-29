package com.example.smartinfantincubator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainScreen extends AppCompatActivity {
    Button loginbtn;
    EditText emailtxt, passwordtxt;
    TextView signuptxt;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        loginbtn = findViewById(R.id.loginbtn);
        emailtxt = findViewById(R.id.emailtxt);
        passwordtxt = findViewById(R.id.passwordtxt);
        signuptxt = findViewById(R.id.signuptxt);
        //connect to firebase
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //Go to signup
        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
        //login method
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectFirebase();
                /*if(emailtxt.getText().toString().equals("waleed") && passwordtxt.getText().toString().equals("123")){
                    Intent intent = new Intent(MainScreen.this,DataScreen.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainScreen.this,"Your password or Email is Wrong!",Toast.LENGTH_LONG).show();
                Log.i("Email, Pass", emailtxt.getText().toString());*/
            }
        });


    }
    public  void connectFirebase(){
        mAuth.signInWithEmailAndPassword(emailtxt.getText().toString(), passwordtxt.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("Login Message: ", "createUserWithEmail:success");
                            Intent intent = new Intent(MainScreen.this,DataScreen.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("Login Message: ", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}