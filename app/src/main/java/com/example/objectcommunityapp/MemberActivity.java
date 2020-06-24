package com.example.objectcommunityapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        findViewById(R.id.member_btn).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.member_btn:
                    pUpdate();
                    break;
            }
        }
    };

    private void pUpdate() {
        String name = ((EditText) findViewById(R.id.member_name)).getText().toString();
        String phone = ((EditText) findViewById(R.id.member_phone)).getText().toString();
        String date = ((EditText) findViewById(R.id.member_date)).getText().toString();
        String address = ((EditText) findViewById(R.id.member_Address)).getText().toString();

        if (name.length() > 0 && phone.length() > 9 && date.length()>5 && address.length()>0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            Memberinfo memberinfo = new Memberinfo(name, phone, date, address);

            if(user != null){
                db.collection("user").document(user.getUid()).set(memberinfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MemberActivity.this, "회원정보를 입력했습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MemberActivity.this, "회원정보 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }else {
            Toast.makeText(MemberActivity.this, "회원정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}