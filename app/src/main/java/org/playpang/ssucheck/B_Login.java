package org.playpang.ssucheck;

import android.content.Intent;
import android.drm.DrmStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class B_Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b__login);

        //타이틀바 변경
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Login to Your App");



        //로그인 버튼을 누르면 메뉴 화면으로 감
        Button btn = findViewById(R.id.B_Login_loginbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), C_Menu.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
