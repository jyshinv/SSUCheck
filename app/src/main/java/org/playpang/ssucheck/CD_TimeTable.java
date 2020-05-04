package org.playpang.ssucheck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CD_TimeTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd__time_table);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("시간표 조회");
        bar.setSubtitle("2020-1학기");
    }
}
