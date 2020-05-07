package org.playpang.ssucheck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.playpang.ssucheck.adapter.CA_RealTimeCheckAdapter;

public class CA_RealTimeCheck extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca__real_time_check);

        //액션바의 제목을 바꿈
        ActionBar bar = getSupportActionBar();
        bar.setTitle("실시간 강의 출결");

        //어댑터 생성
        ListAdapter adapter = new CA_RealTimeCheckAdapter();

        //리스트 뷰 참조 및 Adapter 달기
        ListView lv =findViewById(R.id.ca_real_time_check_lv);
        lv.setAdapter(adapter);

        ((CA_RealTimeCheckAdapter) adapter).addItem("데이터베이스(온&오프) (가,나,다,라)","Database");
        ((CA_RealTimeCheckAdapter) adapter).addItem("컴퓨터구조(나)[214455202]","Computer Architecture");
        ((CA_RealTimeCheckAdapter) adapter).addItem("디지털미디어원리및실습(가)[2154266025]","Digital Media Principles and practice");
        ((CA_RealTimeCheckAdapter) adapter).addItem("컴퓨터프로그래밍(다)[213356026]","Computer Programming");
        ((CA_RealTimeCheckAdapter) adapter).addItem("졸업프로젝트(가)(온&오프)[21589906]","Thesis Seminar");




    }
}
