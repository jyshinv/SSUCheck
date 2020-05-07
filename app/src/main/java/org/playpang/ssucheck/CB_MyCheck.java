package org.playpang.ssucheck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.playpang.ssucheck.adapter.CA_RealTimeCheckAdapter;
import org.playpang.ssucheck.adapter.CB_RealTimeCheckAdapter;

public class CB_MyCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cb__my_check);

        //액션 바 제목 바꾸기
        ActionBar bar = getSupportActionBar();
        bar.setTitle("내 출결 현황");

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
    }
}
