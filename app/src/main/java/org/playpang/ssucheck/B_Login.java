package org.playpang.ssucheck;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.drm.DrmStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class B_Login extends AppCompatActivity {
    EditText id_et;
    EditText pw_et;
    String id;
    String pw;
    Button btn;
    Switch sw;
    Boolean doautologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b__login);

        //타이틀바 변경
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Login to Your App");

        //아이디, 비밀번호, 로그인 버튼
        id_et = findViewById(R.id.b_login_id_et);
        pw_et = findViewById(R.id.b_login_pw_et);
        btn = findViewById(R.id.B_Login_loginbtn);
        sw = findViewById(R.id.b_login_auto_switch);

        //false로 초기화
        doautologin = false;

        //스위치 리스너
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){ //기본 값은 false임, true로 바뀐다면 Boolean값 true저장
                    doautologin = true;
                }else{
                    doautologin = false;
                }
            }
        });


        //자동로그인 기능
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

        //처음에는 SharedPreferences에 아무런 정보도 없으므로 값을 저장할 키들을 생성한다.
        // getString의 첫 번째 인자는 저장될 키, 두 번쨰 인자는 값입니다.
        // 첨엔 값이 없으므로 키값은 원하는 것으로 하시고 값을 null을 줍니다.
        id = auto.getString("inputId",null);
        pw = auto.getString("inputPwd",null);

        //MainActivity로 들어왔을 때 id와 pw값을 가져와서 null이 아니면
        //값을 가져와 id가 20152020이고 pwd가 0000이면 자동적으로 메뉴 화면으로 이동
        if(id !=null && pw != null) {
            if(id.equals("20152020") && pw.equals("0000")) {
                LoginSuccessMessage();
                Intent intent = new Intent(B_Login.this, C_Menu.class);
                startActivity(intent);
                finish();
            }
        }else if(id == null && id == null){//id와 pwd가 null이면 로그인 화면이 보여짐.

            //로그인 버튼 리스너
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    id = id_et.getText().toString();
                    pw = pw_et.getText().toString();


                    if (id.equals("20152020") && pw.equals("0000")) {//로그인 정보 맞을 시

                        if(doautologin){ //스위치 버튼이 on일 경우에만 id와 pw정보를 저장하고 로그인 완료하기
                            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                            //아이디가 '20152020'이고 비밀번호가 '0000'일 경우 SharedPreferences.Editor를 통해
                            //auto의 id와 pw에 값을 저장해 줍니다.
                            SharedPreferences.Editor autoLogin = auto.edit();
                            autoLogin.putString("inputId", id);
                            autoLogin.putString("inputPwd", pw);
                            //꼭 commit()을 해줘야 값이 저장된다.
                            autoLogin.commit();
                        }

                        //스위치 버튼이 off일 경우에는 id와 pw정보 저장하지 않고 로그인 완료하기
                        //로그인 완료
                        //로그인 성공 메세지
                        LoginSuccessMessage();
                        //메뉴화면으로 가기
                        Intent intent = new Intent(getApplicationContext(), C_Menu.class);
                        startActivity(intent);
                        finish();

                    }else{ //로그인 정보가 틀릴 시(20152020,0000)이 아닐 시
                        LoginFailMessage();
                    }
                }
            });


        }


    }


    void LoginFailMessage(){
        Toast.makeText(this,"아이디나 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
    }

    void LoginSuccessMessage(){
        Toast.makeText(this,"학번 : "+id+ "\n환영합니다.",Toast.LENGTH_SHORT).show();
    }

//        //로그인 버튼을 누르면 메뉴 화면으로 감
//        Button btn = findViewById(R.id.B_Login_loginbtn);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //아이디 비밀번호를 String형으로 저장
//                //클릭리스너 바깥에 선언했더니 안됨.
//                //클릭리스너안 onClick메서드 안에 getText를 선언해줘야함
//                id = id_et.getText().toString();
//                pw = pw_et.getText().toString();
//
//                if(id.equals("20152020") && pw.equals("0000") ){
//                    LoginSuccessMessage();
//                    Intent intent = new Intent(getApplicationContext(), C_Menu.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    LoginFailMessage();
//                }
//
//            }
//        });




}
