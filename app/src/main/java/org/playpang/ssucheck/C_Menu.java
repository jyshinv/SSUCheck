package org.playpang.ssucheck;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class C_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__menu);

        //타이틀 바 내용
        ActionBar bar = getSupportActionBar();
        bar.setTitle("숭실대학교 안면인식 출결 시스템~");

        //현재 듣고 있는 과목 버튼 누르면
        TextView tv1 = findViewById(R.id.c_menu_currentsubject);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CA_CurrentSubject.class);
                startActivity(intent);
            }
        });

        //실시간 출결 현황 누르면
        TextView tv2 = findViewById(R.id.c_menu_realtimecheck);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CB_RealTimeCheck.class);
                startActivity(intent);
            }
        });


        //내 출결 현황 누르면
        TextView tv3 = findViewById(R.id.c_menu_mycheck);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CC_MyCheck.class);
                startActivity(intent);
            }
        });


        //시간표 조회 누르면
        //화면 전환
//        TextView tv4 = findViewById(R.id.c_menu_timetable);
//        tv4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),CD_TimeTable.class);
//                startActivity(intent);
//            }
//        });

        //시간표 조회 누르면
        //인터넷으로 연결
        TextView tv4 = findViewById(R.id.c_menu_timetable);
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CD_TimeTableWebView.class);
                startActivity(intent);
            }
        });



    }

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

    //액션버튼 메뉴 액션바에 집어 넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(search_menu);
        getMenuInflater().inflate(R.menu.setting_menu,menu);
        return true;
    }

    //액션버튼을 클릭했을 때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.currentsubject){
            Intent intent = new Intent(getApplicationContext(),CA_CurrentSubject.class);
            startActivity(intent);
           return true;
//        }else if(id == R.id.logout){
//            Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
//            return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
