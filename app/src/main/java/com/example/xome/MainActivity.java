package com.example.xome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public TextView register,login;
    public LinearLayout logInLayout,signUpLayout;
    public EditText logEmail,userName, userPassword, userEmail;
    public EditText logPass;
    private FirebaseAuth firebaseAuth;
    private TextView forgetPass;
    public Button log ,singIn;
    String email, name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.pinkColor));
        }


        logEmail = (EditText) findViewById(R.id.logEmail);
        logPass = (EditText) findViewById(R.id.logPass);
        forgetPass =(TextView) findViewById(R.id.forgetPass);
        register = (TextView) findViewById(R.id.register);
        login = (TextView) findViewById(R.id.login);
        logInLayout = (LinearLayout) findViewById(R.id.logInLayout);
        signUpLayout = (LinearLayout) findViewById(R.id.signUpLayout);
        userName = (EditText)findViewById(R.id.uname);
        userPassword = (EditText)findViewById(R.id.password);
        userEmail = (EditText)findViewById(R.id.EmailAddress);
        log = (Button)findViewById(R.id.Log);
        singIn = (Button)findViewById(R.id.singIn);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register.getResources().getDrawable(R.drawable.switch_tracks);
                register.setTextColor(getResources().getColor(R.color.pinkColor));

                //login= null;
                signUpLayout.setVisibility(View.VISIBLE);
                logInLayout.setVisibility(View.GONE);
                login.setTextColor(getResources().getColor(R.color.pinkColor));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //register = null;
                register.setTextColor(getResources().getColor(R.color.pinkColor));
                login.getResources().getDrawable(R.drawable.switch_tracks);
                signUpLayout.setVisibility(View.GONE);
                logInLayout.setVisibility(View.VISIBLE);
                login.setTextColor(getResources().getColor(R.color.textColor));
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this,home_page.class));
        }

        singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(logEmail.getText().toString(),logPass.getText().toString());
            }
        });

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Password.class));
            }
        });
        firebaseAuth = FirebaseAuth.getInstance();

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                sendEmailVerification();
                                firebaseAuth.signOut();
                                Toast.makeText(MainActivity.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
//                                finish();
                                //startActivity(new Intent(MainActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });



    }

    ////
    private void validate(String username,String userpaswd){


        firebaseAuth.signInWithEmailAndPassword(username,userpaswd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    checkEmailVerification();
                }else {
                    Toast.makeText(MainActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void checkEmailVerification(){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailflag = firebaseUser.isEmailVerified();

        if(emailflag){
            finish();
            startActivity(new Intent(MainActivity.this,home_page.class));
        }else {
            Toast.makeText(this,"Verify yor Email",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
    ////reg
    private Boolean validate(){
        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();

        if(name.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(MainActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
//                        finish();
                    }else{
                        Toast.makeText(MainActivity.this, "Verification mail has not been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(name, email, password);
        myRef.setValue(userProfile);
    }
}