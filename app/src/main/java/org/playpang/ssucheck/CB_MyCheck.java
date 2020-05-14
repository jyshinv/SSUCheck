package org.playpang.ssucheck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.playpang.ssucheck.adapter.CB_RealTimeCheckAdapter;

public class CB_MyCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cb__my_check);

        //액션 바 제목 바꾸기
        ActionBar bar = getSupportActionBar();
        bar.setTitle("내 출결 현황");

        //이전 창에서 보낸 정보 받기(name정보 이전창에서 받음)
        Intent intent = getIntent();
        String name =intent.getStringExtra("name");

        //어댑터 생성
        ListAdapter adapter = new CB_RealTimeCheckAdapter();

        //리스트 뷰 참조 및 Adapter 달기
        ListView lv =findViewById(R.id.cb_my_check_lv);
        lv.setAdapter(adapter);

        ((CB_RealTimeCheckAdapter) adapter).addItem("데이터베이스(온&오프) (가,나,다,라)","Database");
        ((CB_RealTimeCheckAdapter) adapter).addItem("컴퓨터구조(나)[214455202]","Computer Architecture");
        ((CB_RealTimeCheckAdapter) adapter).addItem("디지털미디어원리및실습(가)[2154266025]","Digital Media Principles and practice");
        ((CB_RealTimeCheckAdapter) adapter).addItem("컴퓨터프로그래밍(다)[213356026]","Computer Programming");
        ((CB_RealTimeCheckAdapter) adapter).addItem("졸업프로젝트(가)(온&오프)[21589906]","Thesis Seminar");
        ((CB_RealTimeCheckAdapter) adapter).addName(name);
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
}
