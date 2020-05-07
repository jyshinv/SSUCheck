package org.playpang.ssucheck;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class A_Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__intro);


        //3초 뒤
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),B_Login.class);
                startActivity(intent);
                finish();
            }
        },1000); //1초 후 로그인 화면으로 넘어간다.

       //인트로의 액션바는 가린다.
        ActionBar bar = getSupportActionBar();
        bar.hide();
    }



}
