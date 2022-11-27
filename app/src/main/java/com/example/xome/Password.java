package com.example.xome;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Password extends AppCompatActivity {
    private EditText passWordEmail;
    private Button ResetButton;
    private FirebaseAuth firebaseAuth;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
//status bar
        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.pinkColor));

        passWordEmail = (EditText)findViewById(R.id.ResetEmail);
        ResetButton = (Button) findViewById(R.id.ResetButton);
        firebaseAuth = FirebaseAuth.getInstance();


        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = passWordEmail.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(Password.this,"Please enter your registered Email ID",Toast.LENGTH_LONG).
                            show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Password.this,"Password reset Email sent!",Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(Password.this,MainActivity.class));
                            }else {
                                Toast.makeText(Password.this,"Error in sending password reset Email",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}