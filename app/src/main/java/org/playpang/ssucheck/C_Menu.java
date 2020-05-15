package org.playpang.ssucheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
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
    String currentchild ="Attendance";
    TextView tv1;
    String realname;//id로 받을 name



    //데이터베이스
    private DatabaseReference mDatabaseReference;   // 데이터베이스의 주소를 저장합니다.
    private FirebaseDatabase mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__menu);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //상단의 이름으로 연결
        tv1 = findViewById(R.id.c_menu_name);

        //관람객 추출
        FireBaseValue();

        //타이틀바 숨기기
        ActionBar bar = getSupportActionBar();
        bar.hide();

        //three dot 버튼 눌렀을 때 로그아웃 누르면 작동
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

//                                Toast.makeText(getApplication(),"로그아웃",Toast.LENGTH_SHORT).show();
//                                //SharedPreferences에 저장된 값들을 로그아웃 버튼을 누르면 삭제하기 위해
//                                //SharedPreferences를 불러옵니다. 로그인 화면에서 만든 이름으로
//                                Intent intent = new Intent(C_Menu.this, B_Login.class);
//                                startActivity(intent);
//                                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
//                                SharedPreferences.Editor editor = auto.edit();
//                                //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
//                                editor.clear();
//                                editor.commit();
//                                finish();
                                logout();
                                break;
                            case R.id.m2:
                                setting();
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
                intent.putExtra("name",realname);
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


                //rr.new1에서 attend_관람객이름 또는 late_관람객이름 또는 absence_관람객이름 셋중에 하나로 온다.
                //_를 기준으로 앞은 ar(attendenceresult), realname은 관람객이름이 추출된다.
                int idx = rr.new1.indexOf("_");
                realname = rr.new1.substring(idx +1); //관람객 이름 추출
                //사용할 변수, 대표화면에 이름은 db에서 불러온 이름으로

                tv1.setText(realname +" 20152020");


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


    //logout다이얼로그
    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("로그아웃").setMessage("로그아웃 하시겠습니까?")
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {//오른쪽에 오는 버튼
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {//왼쪽에 오는 버튼
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //확인 누르면 일어나는 이벤트
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
                    }
                }).show();
    }

    //setting
    public void setting(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("누적 출결 데이터베이스 삭제").setMessage("삭제하시겠습니까?")
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {//오른쪽에 오는 버튼
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //취소 누르면 일어나는 이벤트
                        
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {//왼쪽에 오는 버튼
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //확인 누르면 일어나는 이벤트
                        FireBaseDelete();

                    }
                }).show();
    }

    //new1_db_checkresult의 내용을 다 지우는 함수
    private void FireBaseDelete() {

        //파이어베이스 데이터베이스에서 데이터 불러오기
        mFirebaseDatabase = FirebaseDatabase.getInstance();     // 현재 데이터 베이스를 접근할 수 있는 진입점 받기
        mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.child("new1_db_checkresult").setValue(null);
        Toast.makeText(this, "삭제 완료", Toast.LENGTH_SHORT).show();
        


    }


}
