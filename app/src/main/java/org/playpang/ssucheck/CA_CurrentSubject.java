package org.playpang.ssucheck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CA_CurrentSubject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca__current_subject);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("현재 수강중인 과목 검색!!!");
    }
}
