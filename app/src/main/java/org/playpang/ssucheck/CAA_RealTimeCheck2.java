package org.playpang.ssucheck;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.playpang.ssucheck.data.RealTimeAttendenceResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CAA_RealTimeCheck2 extends AppCompatActivity {
    private DatabaseReference mDatabaseReference;   // 데이터베이스의 주소를 저장합니다.
    private FirebaseDatabase mFirebaseDatabase;
    Intent intent;
    TextView time;
    TextView junsuAttend;
    TextView jillkimAttend;
    TextView jiwonAttend;
    TextView junsuAbsence;
    TextView jillkimAbsence;
    TextView jiwonAbsence;
    TextView junsulate;
    TextView jillkimlate;
    TextView jiwonlate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caa__real_time_check2);

        //변수
        time = findViewById(R.id.caa_real_time_check2_time);

        junsuAttend = findViewById(R.id.caa_real_time_check2_junsu_attend);
        junsulate = findViewById(R.id.caa_real_time_check2_junsu_late);
        junsuAbsence = findViewById(R.id.caa_real_time_check2_junsu_absence);

        jillkimAttend = findViewById(R.id.caa_real_time_check2_jillkim_attend);
        jillkimlate = findViewById(R.id.caa_real_time_check2_jillkim_late);
        jillkimAbsence = findViewById(R.id.caa_real_time_check2_jillkim_absence);

        jiwonAttend = findViewById(R.id.caa_real_time_check2_jiwon_attend);
        jiwonlate = findViewById(R.id.caa_real_time_check2_jiwon_late);
        jiwonAbsence = findViewById(R.id.caa_real_time_check2_jiwon_absence);



        //이전 창에서 보낸 정보 받기
        intent = getIntent();
        String kor =intent.getStringExtra("kor");
        String eng =intent.getStringExtra("eng");


        //액션바에 제목 달기
        ActionBar bar = getSupportActionBar();
        bar.setTitle(kor);
        bar.setSubtitle(eng);

        //날짜,시간 달기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currenttime = mFormat.format(date);
        time.setText(currenttime);

        //파이어베이스 데이터베이스에서 데이터 불러오기
        mFirebaseDatabase = FirebaseDatabase.getInstance();     // 현재 데이터 베이스를 접근할 수 있는 진입점 받기
        mDatabaseReference = mFirebaseDatabase.getReference();


        mDatabaseReference.child("AttendanceTest").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                RealTimeAttendenceResult rr = dataSnapshot.getValue(RealTimeAttendenceResult.class);
                //Log.d("FB","FB");
                if(rr.jillkim.equals("absence")){
                    jillkimAttend.setTextColor(Color.parseColor("#F13C00")); //결석은 빨간색
                }else if(rr.jillkim.equals("attend")){
                    jillkimAttend.setTextColor(Color.parseColor("#FF2EFF36")); //출석은 파란색
                }else if(rr.jillkim.equals("late")){
                    jillkimlate.setTextColor(Color.parseColor("#FF9800")); //지각은 노란색
                }

                if(rr.jiwonkim.equals("absence")){
                    jiwonAbsence.setTextColor(Color.parseColor("#F13C00"));
                }else if(rr.jiwonkim.equals("attend")){
                    jiwonAttend.setTextColor(Color.parseColor("#FF2EFF36"));
                }else{
                    jiwonlate.setTextColor(Color.parseColor("#FF9800"));
                }

                if(rr.junsu.equals("absence")){
                    junsuAbsence.setTextColor(Color.parseColor("#F13C00"));
                }else if(rr.junsu.equals("attend")){
                    junsuAttend.setTextColor(Color.parseColor("#FF2EFF36"));
                }else{
                    junsulate.setTextColor(Color.parseColor("#FF9800"));
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }


}
