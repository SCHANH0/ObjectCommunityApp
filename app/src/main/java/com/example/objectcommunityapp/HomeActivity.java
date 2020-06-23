package com.example.objectcommunityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

class Board {
    String title;
    String content;

    Board(){}

    Board(String title, String content){
        this.title=title;
        this.content=content;
    }
    public String getTitle(){return title;}
    public String getContent(){return content;}
}

public class HomeActivity extends AppCompatActivity {
    TextView mTextLogo;
    Button sBtn;
    EditText sText,sText2;
    RecyclerView listView1;
    FirebaseAuth mAuth;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(HomeActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            for (UserInfo profile : user.getProviderData()){
                String name = profile.getDisplayName();
                if (name == null) {
                    Intent memintent = new Intent(HomeActivity.this, MemberActivity.class);
                    startActivity(memintent);
                }
            }
        }
        Toast.makeText(HomeActivity.this,"환영합니다!!", Toast.LENGTH_SHORT).show();
        mTextLogo = (TextView) findViewById(R.id.text_logo);
        sBtn=(Button)findViewById(R.id.search_btn);
        sText=(EditText)findViewById(R.id.search_text);
        sText2=(EditText)findViewById(R.id.search_text2);
        mAuth = FirebaseAuth.getInstance();;

        sBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(HomeActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}