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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CD_ChangeCheck extends AppCompatActivity {

    TextView date;
    EditText et1;
    Button okbtn, cancelbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd__change_check);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("출결 변경 신청");

        date = findViewById(R.id.tv_date);
        et1 = findViewById(R.id.et);
        okbtn = findViewById(R.id.ok_btn);
        cancelbtn = findViewById(R.id.cancel_btn);

        date.setText(currentTime());
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(null);
                Toast.makeText(CD_ChangeCheck.this, "출결 변경 완료", Toast.LENGTH_SHORT).show();

            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(null);
                Toast.makeText(CD_ChangeCheck.this, "취소하였습니다.", Toast.LENGTH_SHORT).show();

            }
        });


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

    //현재 시간을 String값으로 리턴해주는 함수
    public String currentTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = mFormat.format(date);
        return currentTime;
    }
}


