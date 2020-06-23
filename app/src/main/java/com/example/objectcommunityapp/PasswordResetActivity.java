package com.example.objectcommunityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PasswordResetActivity extends AppCompatActivity {
    private TextView join, login;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordreset);

        mAuth = FirebaseAuth.getInstance();

        join = (TextView) findViewById(R.id.textview_register);
        login = (TextView) findViewById(R.id.textview_login);

        findViewById(R.id.button_pass_reset).setOnClickListener(onClickListener);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(PasswordResetActivity.this, ResisterActivity.class);
                startActivity(registerIntent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(PasswordResetActivity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_pass_reset:
                    send();
                    break;
            }
        }
    };

    private void send() {
        String email = ((EditText) findViewById(R.id.edittext_username)).getText().toString();

        if (email.length() > 0) {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(PasswordResetActivity.this, "이메일을 확인해주세요.", Toast.LENGTH_SHORT).show();
                        Intent LoginIntent = new Intent(PasswordResetActivity.this,MainActivity.class);
                        startActivity(LoginIntent);
                    }
                }
            });
        } else {
            Toast.makeText(PasswordResetActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }

    }
}