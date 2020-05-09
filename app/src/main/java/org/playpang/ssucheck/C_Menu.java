package org.playpang.ssucheck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

public class C_Menu extends AppCompatActivity {

    //사용할 변수
    LinearLayout ll;
    Intent intent;
    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__menu);


        //타이틀바 숨기기
        ActionBar bar = getSupportActionBar();
        bar.hide();

        //three dot 버튼 눌렀을 때 작동
        btn = findViewById(R.id.c_menu_threedot);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup= new PopupMenu(getApplicationContext(), v);//v는 클릭된 뷰를 의미

                getMenuInflater().inflate(R.menu.three_dot_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.m1:
                                Toast.makeText(getApplication(),"로그아웃",Toast.LENGTH_SHORT).show();
                                //SharedPreferences에 저장된 값들을 로그아웃 버튼을 누르면 삭제하기 위해
                                //SharedPreferences를 불러옵니다. 로그인 화면에서 만든 이름으로
                                Intent intent = new Intent(C_Menu.this, B_Login.class);
                                startActivity(intent);
                                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = auto.edit();
                                //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
                                editor.clear();
                                editor.commit();
                                finish();

                                break;
                            case R.id.m2:
                                Toast.makeText(getApplication(),"settings",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });

                popup.show();//Popup Menu 보이기


            }
        });

        //main화면 4개의 버튼 누를 시 각 창으로 이동
        //첫번째 버튼
        ll = findViewById(R.id.c_menu_first_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),CA_RealTimeCheck.class);
                startActivity(intent);
            }
        });

        //두번째 버튼
        ll = findViewById(R.id.c_menu_second_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),CB_MyCheck.class);
                startActivity(intent);
            }
        });

        //세번째 버튼
        ll = findViewById(R.id.c_menu_third_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),CC_TimeTable.class);
                startActivity(intent);
            }
        });

        //네번째 버튼
        ll = findViewById(R.id.c_menu_fourth_ll);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),CD_ChangeCheck.class);
                startActivity(intent);
            }
        });















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
