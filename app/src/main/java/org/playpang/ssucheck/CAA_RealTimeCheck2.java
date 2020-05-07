package org.playpang.ssucheck;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CAA_RealTimeCheck2 extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caa__real_time_check2);

        //이전 창에서 보낸 정보 받기
        intent = getIntent();
        String kor =intent.getStringExtra("kor");
        String eng =intent.getStringExtra("eng");


        //액션바에 제목 달기
        ActionBar bar = getSupportActionBar();
        bar.setTitle(kor);
        bar.setSubtitle(eng);


    }
}
