package org.playpang.ssucheck;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CD_TimeTableWebView extends AppCompatActivity {

    private WebView mwv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd__time_table_web_view);

        //title 바꾸기
        ActionBar bar = getSupportActionBar();
        bar.setTitle("시간표 조회");
        bar.setSubtitle("2020-1학기");

        //웹뷰 띄우기
        mwv = (WebView)findViewById(R.id.cd_TimeTable);
        WebSettings mws = mwv.getSettings();//Mobile Web Settings
        mws.getJavaScriptEnabled(); //자바 스크립트 허용
        mws.setLoadWithOverviewMode(true); //컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞춰 조정

        mwv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mwv.loadUrl("https://everytime.kr/");

        //위 setWebViewClient 안넣고 아래 한줄만 넣어도 들어가지긴 했음
        //mwv.loadUrl("https://everytime.kr/");

    }

    //이 코드를 추가하지 않으면 뒤로가기 이벤트 호출시 이전 화면으로 전환됨(어플의 메인메뉴)
    //이 코드를 추가하면 웹 내에서 뒤로가기가 가능해짐
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mwv.canGoBack()) {
                mwv.goBack();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
