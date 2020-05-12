package org.playpang.ssucheck;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.playpang.ssucheck.data.DatabaseCheckResult;
import org.playpang.ssucheck.data.FirebasePost;
import org.playpang.ssucheck.data.RealTimeAttendenceResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class CAA_RealTimeCheck2 extends AppCompatActivity {
    private DatabaseReference mDatabaseReference;   // 데이터베이스의 주소를 저장합니다.
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference2;   // 데이터베이스의 주소를 저장합니다.
    private FirebaseDatabase mFirebaseDatabase2;

    Intent intent;
    TextView[] textView = new TextView[24];
    TextView time;
    TextView new1tv;
    TextView new2tv;
    TextView new3tv;

    boolean checkFlag = true;

    //색상 변수
    String Green = "#1CC62F";
    String Orange = "#FFBA5C";
    String Red = "#FF1010";
    String DarkGray = "#47525E";

    //Child변수
    String currentchild = "AttendanceTest";
    String ckresult;

    private SwipeRefreshLayout swipe;
    private int cnt =1;

    int count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caa__real_time_check2);




        //변수
        time = findViewById(R.id.caa_real_time_check2_time);

        textView[0] = findViewById(R.id.caa_real_time_check2_jiwon_attend);
        textView[1] = findViewById(R.id.caa_real_time_check2_jiwon_late);
        textView[2] = findViewById(R.id.caa_real_time_check2_jiwon_absence);

        textView[3] = findViewById(R.id.caa_real_time_check2_jiilkim_attend);
        textView[4] = findViewById(R.id.caa_real_time_check2_jiilkim_late);
        textView[5] = findViewById(R.id.caa_real_time_check2_jiilkim_absence);

        textView[6] = findViewById(R.id.caa_real_time_check2_jiyoon_attend);
        textView[7] = findViewById(R.id.caa_real_time_check2_jiyoon_late);
        textView[8] = findViewById(R.id.caa_real_time_check2_jiyoon_absence);

        textView[9] = findViewById(R.id.caa_real_time_check2_sp_attend);
        textView[10] = findViewById(R.id.caa_real_time_check2_sp_late);
        textView[11] = findViewById(R.id.caa_real_time_check2_sp_absence);

        textView[12] = findViewById(R.id.caa_real_time_check2_dd_attend);
        textView[13] = findViewById(R.id.caa_real_time_check2_dd_late);
        textView[14] = findViewById(R.id.caa_real_time_check2_dd_absence);

        new1tv = findViewById(R.id.caa_real_time_check2_new1);
        textView[15] = findViewById(R.id.caa_real_time_check2_new1_attend);
        textView[16] = findViewById(R.id.caa_real_time_check2_new1_late);
        textView[17] = findViewById(R.id.caa_real_time_check2_new1_absence);

        new2tv = findViewById(R.id.caa_real_time_check2_new2);
        textView[18] = findViewById(R.id.caa_real_time_check2_new2_attend);
        textView[19] = findViewById(R.id.caa_real_time_check2_new2_late);
        textView[20] = findViewById(R.id.caa_real_time_check2_new2_absence);

        new3tv = findViewById(R.id.caa_real_time_check2_new3);
        textView[21] = findViewById(R.id.caa_real_time_check2_new3_attend);
        textView[22] = findViewById(R.id.caa_real_time_check2_new3_late);
        textView[23] = findViewById(R.id.caa_real_time_check2_new3_absence);


        //날짜,시간 달기
        time.setText(currentTime());

        //파이어베이스
        FireBaseSingle();

        //if(checkFlag==true) {
            FireBaseValue();
        //}



        //새로고침
        swipe = findViewById(R.id.caa_real_time_check2_ll);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {//새로고침 했을 때 내용
                //새로고침 시 바뀌는 내용1 - 시간
                time.setText(currentTime());


                //새로고침 시 바뀌는 내용2 - textview 전부 다시 darkgray로 설정
                for(int i=0; i<24; i++){
                    textView[i].setTextColor(Color.parseColor(DarkGray));
                }

                //새로고침 시 바뀌는 내용3 - db내용
                FireBaseSingle();

                //새로 고침 완료했을 땐 꼭 setRefreshing함수를 false로 설정해줘야함
                swipe.setRefreshing(false);


            }
        });


        //이전 창에서 보낸 정보 받기
        intent = getIntent();
        String kor =intent.getStringExtra("kor");
        String eng =intent.getStringExtra("eng");


        //액션바에 제목 달기
        ActionBar bar = getSupportActionBar();
        bar.setTitle(kor);
        bar.setSubtitle(eng);






    } //onCreate


    //5명에 대한 함수
    public void AR(String value, TextView Attend, TextView Late, TextView Absence ){
        if(value != null){ //값이 db로 부터 넘어온다면
            if(value.equals("attend")){
                Attend.setTextColor(Color.parseColor(Green)); //출석은 그린
            }else if(value.equals("late")){
                Late.setTextColor(Color.parseColor(Orange)); //지각은 주황
            }else if(value.equals("absence")){
                Absence.setTextColor(Color.parseColor(Red)); //결석은 레드
            }else{ //before check 상태
                //아무일도 일어나지 않음
            }

        }else{ //값이 db로부터 넘어오지 않는 경우, 5명은 무조건 넘어옴 (사진 5개는 무조건 있음)
            //혹시 null일 경우 대비해서 놔두기

        }
    }

    //new1, new2, new3에 대한 함수
    public void AR_new(String value,TextView nametv ,TextView Attend, TextView Late, TextView Absence){


        if(value != null){ //값이 db로 부터 넘어온다면

            //value는 attend_관람객이름 또는 late_관람객이름 또는 absence_관람객이름 셋중에 하나로 온다.
            //_를 기준으로 앞은 ar(attendenceresult), name은 관람객이름이 추출된다.
            int idx = value.indexOf("_");
            String ar = value.substring(0,idx);
            String name = value.substring(idx +1);

            //받아온 관람객 이름을 기준으로 화면에 띄움
            nametv.setText(name);

            //결과를 각 색으로 바꾸어 화면에 출력
            if(ar.equals("attend")){
                Attend.setTextColor(Color.parseColor(Green)); //출석은 그린
            }else if(ar.equals("late")){
                Late.setTextColor(Color.parseColor(Orange)); //지각은 주황
            }else if(ar.equals("absence")){
                Absence.setTextColor(Color.parseColor(Red)); //결석은 레드
            }else{ //before check 상태
                //아무일도 일어나지 않음
            }

        }else{ //값이 db로부터 넘어오지 않는다면 결석인 상태임
            Absence.setTextColor(Color.parseColor(Red));
        }
    }

    //현재 시간을 String값으로 리턴해주는 함수
    public String currentTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = mFormat.format(date);
        return currentTime;
    }

    //Attendance의 값이 변경될 때마다 그 시간을 db에 넣기
    public void post(String checkresult){
        Map<String, Object> childUpdates = new HashMap<>();
        Map<String, Object> postValues = null;
        DatabaseCheckResult post = new DatabaseCheckResult(currentTime(),checkresult);
        postValues = post.toMap();

        childUpdates.put("/jwkim_db_checkresult/" + currentTime(), postValues);
        mDatabaseReference.updateChildren(childUpdates);
    }


    public void FireBaseSingle(){
        //파이어베이스 데이터베이스에서 데이터 불러오기
        mFirebaseDatabase = FirebaseDatabase.getInstance();     // 현재 데이터 베이스를 접근할 수 있는 진입점 받기
        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.child(currentchild).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                RealTimeAttendenceResult rr = dataSnapshot.getValue(RealTimeAttendenceResult.class);

                //주의 사항
                //rr.~에 데이터가 안넘어 오는 경우 -> NullPointerException 생기면서 앱꺼짐 현상 생김
                //따라서 꼭 rr.~ != null인 경우로 예외 처리 넣어주기

                //함수 호출
                //출결결과, 3개의 TextView를 넘김
                AR(rr.jiwonkim,textView[0],textView[1],textView[2]);
                AR(rr.jiilkim,textView[3],textView[4],textView[5]);
                AR(rr.jiyoonshin,textView[6],textView[7],textView[8]);
                AR(rr.spongebob,textView[9],textView[10],textView[11]);
                AR(rr.ddunge,textView[12],textView[13],textView[14]);

                //추가로 관람객 3명의 결과
                AR_new(rr.new1,new1tv,textView[15],textView[16],textView[17]);
                AR_new(rr.new2,new2tv,textView[18],textView[19],textView[20]);
                AR_new(rr.new3,new3tv,textView[21],textView[22],textView[23]);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void FireBaseValue() {
        //파이어베이스 데이터베이스에서 데이터 불러오기
        mFirebaseDatabase = FirebaseDatabase.getInstance();     // 현재 데이터 베이스를 접근할 수 있는 진입점 받기
        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference2 = mFirebaseDatabase.getReference();
        //final String key = mDatabaseReference2.child(currentchild).child("jiwonkim").getKey();
        //Log.e("key값",key);


        mDatabaseReference.child(currentchild).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RealTimeAttendenceResult rr = dataSnapshot.getValue(RealTimeAttendenceResult.class);
                //String arr[] =new String[8];
                //주의 사항
                //rr.~에 데이터가 안넘어 오는 경우 -> NullPointerException 생기면서 앱꺼짐 현상 생김
                //따라서 꼭 rr.~ != null인 경우로 예외 처리 넣어주기'
//                boolean checkFlag2=false;
//                int count=0;
//                if(rr.jiwonkim.equals("bc")) {
//                    checkFlag2 = true;
//                }



                //지켜보다가 값이 변경되면 post해주는 함수
                //그냥 post함수 쓰면 jiwonkim외에 다른 사람이 변경되어도 아래 if문이 실행됨
//                if(checkFlag2==true){
//                    if (!rr.jiwonkim.equals("bc")) { //jiwonkim이 attend, late, absence일 때
//                        post(rr.jiwonkim); //post함수 호출
//                        //checkFlag = false;
//                    }else { //jiwonkim이 bc일 때
//                        //checkFlag = true;
//                    }
//                }


                if(count==0) {
                    if (!rr.jiwonkim.equals("bc")) {
                        post(rr.jiwonkim);
                        count++;
                    }
                }
                if(rr.jiwonkim.equals("bc")){
                    count =0;
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
            Intent intent = new Intent(getApplicationContext(), CA_RealTimeCheck.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }












}//class
