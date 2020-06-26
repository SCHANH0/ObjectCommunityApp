package com.example.objectcommunityapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
    private static final String TAG = "HomeActivity";
    TextView mTextLogo;
    Button sBtn,oBtn,mBtn,cBtn,rBtn;
    EditText sText;
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
            myStartActivity(MainActivity.class);
        }else {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null) {
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                //myStartActivity(MemberActivity.class);
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }
        mTextLogo = (TextView) findViewById(R.id.text_logo);
        sBtn=(Button)findViewById(R.id.search_btn);
        oBtn=(Button)findViewById(R.id.out_btn);
        mBtn=(Button)findViewById(R.id.member_btn);
        rBtn=(Button)findViewById(R.id.start_btn);
        cBtn=(Button)findViewById(R.id.chshok_btn);
        sText=(EditText)findViewById(R.id.search_text);
        mAuth = FirebaseAuth.getInstance();;

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        oBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(HomeActivity.this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myStartActivity(MemberActivity.class);
            }
        });
        rBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myStartActivity(Home2Activity.class);
            }
        });
        cBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myStartActivity(CashbackActivity.class);
            }
        });

    }
    private void myStartActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}