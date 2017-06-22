package com.anjinma.numberpang;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExitActivity extends Activity implements View.OnClickListener{
    TextView btnYes = null;
    TextView btnNo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        btnYes = (TextView) findViewById(R.id.Yes);
        btnNo = (TextView) findViewById(R.id.No);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Yes:
                android.os.Process.killProcess(android.os.Process.myPid());
                break;

            case R.id.No:
                finish();
                break;
        }
    }
}
