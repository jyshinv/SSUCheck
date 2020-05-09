package org.playpang.ssucheck;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CBA_MyCheckSubject1 extends AppCompatActivity {

    Intent intent;
    private DatabaseReference mDatabaseReference;   // 데이터베이스의 주소를 저장합니다.
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cba__my_check_subject1);

        //이전 창에서 보낸 정보 받기
        intent = getIntent();
        String kor =intent.getStringExtra("kor");
        String eng =intent.getStringExtra("eng");


        //액션바에 제목 달기
        ActionBar bar = getSupportActionBar();
        bar.setTitle(kor + " 출결현황");
        bar.setSubtitle(eng);

        //파이어베이스 데이터베이스에서 데이터 불러오기
        mFirebaseDatabase = FirebaseDatabase.getInstance();     // 현재 데이터 베이스를 접근할 수 있는 진입점 받기
        mDatabaseReference = mFirebaseDatabase.getReference();

        mDatabaseReference.child("Attendance").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
