package org.playpang.ssucheck;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CD_ChangeCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cd__change_check);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("출결 변경 신청");
    }
}
