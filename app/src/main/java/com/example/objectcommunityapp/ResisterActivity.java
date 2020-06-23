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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResisterActivity extends AppCompatActivity {
    TextView mTextViewLogin;
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
        setContentView(R.layout.activity_resister);

        mAuth = FirebaseAuth.getInstance();

        mTextViewLogin = (TextView)findViewById(R.id.textview_login);
        findViewById(R.id.button_register).setOnClickListener(onClickListener);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(ResisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_register:
                    signUp();
                    break;
            }
        }
    };

    private void signUp(){
        String email = ((EditText)findViewById(R.id.edittext_username)).getText().toString();
        String password = ((EditText)findViewById(R.id.edittext_password)).getText().toString();
        String passwordch = ((EditText)findViewById(R.id.edittext_cnf_password)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordch.length() > 0){
            if(password.equals(passwordch)){
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(ResisterActivity.this, "회원가입 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ResisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else {
                            if(task.getException() !=null){
                                Toast.makeText(ResisterActivity.this, "회원가입 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }else {
                Toast.makeText(ResisterActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(ResisterActivity.this, "이메일 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}