package org.playpang.ssucheck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.playpang.ssucheck.data.DatabaseCheckResult;
import org.playpang.ssucheck.data.RealTimeAttendenceResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class C_Menu extends AppCompatActivity {

    //사용할 변수
    LinearLayout ll;
    Intent intent;
    ImageButton btn;
    String currentchild ="AttendanceTest";

    //데이터베이스
    private DatabaseReference mDatabaseReference;   // 데이터베이스의 주소를 저장합니다.
    private FirebaseDatabase mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__menu);

        //메뉴화면에서도 바뀌게
        FireBaseValue();


        //타이틀바 숨기기
        ActionBar bar = getSupportActionBar();
        bar.hide();

        //three dot 버튼 눌렀을 때 작동
        btn = findViewById(R.id.c_menu_threedot);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.three_dot_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.m1:
                                Toast.makeText(getApplication(),"로그아웃",Toast.LENGTH_SHORT).show();
                                //SharedPreferences에 저장된 값들을 로그아웃 버튼을 누르면 삭제하기 위해
                                //SharedPreferences를 불러옵니다. 로그인 화면에서 만든 이름으로
                                Intent intent = new Intent(C_Menu.this, B_Login.class);
                                startActivity(intent);
                                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = auto.edit();
                                //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
                                editor.clear();
                                editor.commit();
                                finish();

                                break;
                            case R.id.m2:
                                Toast.makeText(getApplication(),"settings",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });

                popup.show();//Popup Menu 보이기


            }
        });

        //main화면 4개의 버튼 누를 시 각 창으로 이동
        //첫번째 버튼
        ll = findViewById(R.id.c_menu_first_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),CA_RealTimeCheck.class);
                startActivity(intent);
            }
        });

        //두번째 버튼
        ll = findViewById(R.id.c_menu_second_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),CB_MyCheck.class);
                startActivity(intent);
            }
        });

        //세번째 버튼
        ll = findViewById(R.id.c_menu_third_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),CC_TimeTable.class);
                startActivity(intent);
            }
        });

        //네번째 버튼
        ll = findViewById(R.id.c_menu_fourth_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),CD_ChangeCheck.class);
                startActivity(intent);
            }
        });





    }



    //2번 누르면 종료되게 만드는 코드
    long pressTime;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(System.currentTimeMillis() - pressTime < 2000){
            finishAffinity();
            return;
        }
        Toast.makeText(this,"한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show();
        pressTime = System.currentTimeMillis();
    }

    public void FireBaseValue() {
        //파이어베이스 데이터베이스에서 데이터 불러오기
        mFirebaseDatabase = FirebaseDatabase.getInstance();     // 현재 데이터 베이스를 접근할 수 있는 진입점 받기
        mDatabaseReference = mFirebaseDatabase.getReference();


        mDatabaseReference.child(currentchild).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RealTimeAttendenceResult rr = dataSnapshot.getValue(RealTimeAttendenceResult.class);

                //주의 사항
                //rr.~에 데이터가 안넘어 오는 경우 -> NullPointerException 생기면서 앱꺼짐 현상 생김
                //따라서 꼭 rr.~ != null인 경우로 예외 처리 넣어주기

                //함수 호출
                //출결결과, 3개의 TextView를 넘김
//                AR(rr.jiwonkim,textView[0],textView[1],textView[2]);
//                AR(rr.jiilkim,textView[3],textView[4],textView[5]);
//                AR(rr.jiyoonshin,textView[6],textView[7],textView[8]);
//                AR(rr.spongebob,textView[9],textView[10],textView[11]);
//                AR(rr.ddunge,textView[12],textView[13],textView[14]);
//
//                //추가로 관람객 3명의 결과
//                AR_new(rr.new1,new1tv,textView[15],textView[16],textView[17]);
//                AR_new(rr.new2,new2tv,textView[18],textView[19],textView[20]);
//                AR_new(rr.new3,new3tv,textView[21],textView[22],textView[23]);

                //지켜보다가 값이 변경되면 post해주는 함수
                //그냥 post함수 쓰면 jiwonkim외에 다른 사람이 변경되어도 아래 if문이 실행됨
                if (!rr.jiwonkim.equals("bc")) {
                    post(rr.jiwonkim); //post함수 호출
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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




}
