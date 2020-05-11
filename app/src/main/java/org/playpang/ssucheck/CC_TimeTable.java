package org.playpang.ssucheck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CC_TimeTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc__time_table);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("시간표 조회");
        bar.setSubtitle("2020-1학기");


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
