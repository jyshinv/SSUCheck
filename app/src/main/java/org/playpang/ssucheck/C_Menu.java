package org.playpang.ssucheck;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class C_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__menu);

        //타이틀바 색상 변경
        ActionBar bar = getSupportActionBar();
        bar.hide();














    }



    //2번 누르면 종료되게 만드는 코드
    long pressTime;
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(System.currentTimeMillis() - pressTime < 2000){
            finishAffinity();
            return;
        }
        Toast.makeText(this,"한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show();
        pressTime = System.currentTimeMillis();
    }

//    //액션버튼 메뉴 액션바에 집어 넣기
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //return super.onCreateOptionsMenu(search_menu);
//        getMenuInflater().inflate(R.menu.setting_menu,menu);
//        return true;
//    }
//
//    //액션버튼을 클릭했을 때의 동작
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if(id == R.id.currentsubject){
//            Intent intent = new Intent(getApplicationContext(),CA_CurrentSubject.class);
//            startActivity(intent);
//           return true;
////        }else if(id == R.id.logout){
////            Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
////            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//
//    }
}
