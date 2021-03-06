package org.playpang.ssucheck;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.playpang.ssucheck.adapter.CA_RealTimeCheckAdapter;
import org.playpang.ssucheck.adapter.CBA_MyCheckSubject1Adapter;
import org.playpang.ssucheck.data.DatabaseCheckResult;
import org.playpang.ssucheck.data.FirebasePost;
import org.playpang.ssucheck.data.jw_db_checkresult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class CBA_MyCheckSubject1 extends AppCompatActivity {

    //변수 선언
    Intent intent;
    private DatabaseReference mDatabaseReference;   // 데이터베이스의 주소를 저장합니다.
    private FirebaseDatabase mFirebaseDatabase;

    //담아올 데이터 변수 입력
    ArrayList<String> checkTime = new ArrayList<>();
    ArrayList<String> checkresult = new ArrayList<>();

    //어댑터 생성
    final ListAdapter adapter = new CBA_MyCheckSubject1Adapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cba__my_check_subject1);

        //이전 창에서 보낸 정보 받기
        intent = getIntent();
        String kor =intent.getStringExtra("kor");
        String eng =intent.getStringExtra("eng");
        String name = intent.getStringExtra("name");

        //받은 이름 상단 이름에 붙이기
        TextView nametv = findViewById(R.id.name);
        nametv.setText(name);


        //액션바에 제목 달기
        ActionBar bar = getSupportActionBar();
        bar.setTitle(kor + " 출결현황");
        bar.setSubtitle(eng);

        //리스트 뷰 참조 및 Adapter 달기
        ListView lv =findViewById(R.id.cba_my_check_subject1_lv);
        lv.setAdapter(adapter);



        //((CBA_MyCheckSubject1Adapter) adapter).addItem("날짜","지각");
        //파이어베이스 데이터베이스에서 데이터 불러오기
        mFirebaseDatabase = FirebaseDatabase.getInstance();     // 현재 데이터 베이스를 접근할 수 있는 진입점 받기
        mDatabaseReference = mFirebaseDatabase.getReference();
        getFirebaseDatabase();




    }//onCreate

    private void getFirebaseDatabase(){
        final ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    jw_db_checkresult jdc = snapshot.getValue(jw_db_checkresult.class);
                    String[] result = {jdc.checkTime, jdc.checkResult};

                    ((CBA_MyCheckSubject1Adapter) adapter).addItem(result[0], result[1]);
                    ((CBA_MyCheckSubject1Adapter) adapter).notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.child("new1_db_checkresult").addListenerForSingleValueEvent(postListener);


    }

    private void getFirebaseDatabaseValue(){
        final ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    jw_db_checkresult jdc = snapshot.getValue(jw_db_checkresult.class);
                    String[] result = {jdc.checkTime, jdc.checkResult};

                    ((CBA_MyCheckSubject1Adapter) adapter).addItem(result[0], result[1]);
                    ((CBA_MyCheckSubject1Adapter) adapter).notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.child("new1_db_checkresult").addValueEventListener(postListener);


    }


    //현재 시간을 String값으로 리턴해주는 함수
    public String currentTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = mFormat.format(date);
        return currentTime;

    }

    //액션바에 버튼 집어넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_btn, menu);
        return true;
    }


    //버튼 눌렀을 때의 반응
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.back_btn){
            Intent intent = new Intent(getApplicationContext(), CB_MyCheck.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }




}//class
