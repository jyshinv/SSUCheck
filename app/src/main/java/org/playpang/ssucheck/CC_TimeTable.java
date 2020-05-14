package org.playpang.ssucheck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class CC_TimeTable extends AppCompatActivity {

    LinearLayout ll[] = new LinearLayout[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc__time_table);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("시간표 조회");
        bar.setSubtitle("2020-1학기");

//        ll[0] = findViewById(R.id.db1);
//        ll[1] = findViewById(R.id.db2);
//        ll[2] = findViewById(R.id.digital1);
//        ll[3] = findViewById(R.id.digital2);
//        ll[4] = findViewById(R.id.computer1);
//        ll[5] = findViewById(R.id.computer2);
//        ll[6] = findViewById(R.id.computerP);
//        ll[7] = findViewById(R.id.project);



//        ll[0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timetable(0);
//            }
//        });
//
//
//        ll[1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timetable(1);
//            }
//        });
//
//        ll[2].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timetable(2);
//            }
//        });
//
//        ll[3].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timetable(3);
//            }
//        });
//
//        ll[4].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timetable(4);
//            }
//        });
//
//        ll[5].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timetable(5);
//            }
//        });
//
//        ll[6].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timetable(6);
//            }
//        });
//
//        ll[7].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                timetable(7);
//            }
//        });









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
            Intent intent = new Intent(getApplicationContext(), C_Menu.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void timetable(int i){

        String subject = "과목명";
        String db = "데이터베이스(온&오프) (가,나,다,라)";
        String digital ="디지털미디어원리및실습(가)[2154266025]";
        String computer = "컴퓨터구조(나)[214455202]";
        String computerP = "컴퓨터프로그래밍(다)[213356026]";
        String project ="졸업프로젝트(가)(온&오프)[21589906]";
        if(i==0 || i==1){
            subject = db;
        }else if(i==2|| i==3){
            subject = digital;
        }else if(i==4|| i==5){
            subject = computer;
        }else if(i==6){
            subject = computerP;
        }else if(i==7){
            subject = project;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("과목 정보").setMessage("과목명 : " +subject+ "\n담당교수명 : 홍길동")
                .setNegativeButton("확인", new DialogInterface.OnClickListener() {//왼쪽에 오는 버튼
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //확인 누르면 일어나는 이벤트


                    }
                }).show();
    }
}
