package com.anjinma.numberpang;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PauseActivity extends Activity implements View.OnClickListener {
    TextView restartText = null;
    TextView mainmenuText = null;
    TextView continueText = null;
    String mode = null;
    MediaPlayer mp = null;
    SharedPreferences backgroundPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        restartText = (TextView) findViewById(R.id.Restart);
        mainmenuText = (TextView) findViewById(R.id.Mainmenu);
        continueText = (TextView) findViewById(R.id.Continue);
        restartText.setOnClickListener(this);
        mainmenuText.setOnClickListener(this);
        continueText.setOnClickListener(this);

        Intent modeIntent = getIntent();
        mode = modeIntent.getStringExtra("modePAUSE");
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Restart:
                Intent restartIntent = null;
                if(mode.equals("infinite")) {
                    restartIntent = new Intent(getApplicationContext(), InfiniteActivity.class);}
                else if(mode.equals("challenge")) {
                    restartIntent = new Intent(getApplicationContext(), ChallengeActivity.class);}
                else if(mode.equals("timeattack")) {
                    restartIntent = new Intent(getApplicationContext(), TimeAttackActivity.class);}
                else if(mode.equals("hell")) {
                    restartIntent = new Intent(getApplicationContext(), HellActivity.class);}
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(restartIntent);
                finish();
                break;

            case R.id.Mainmenu:
                Intent mainmenuIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainmenuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainmenuIntent);
                finish();
                break;

            case R.id.Continue:
                finish();
                break;
        }
    }

    public void onResume() {
        if(mp == null) {
            mp = MediaPlayer.create(this, R.raw.pausebgm);
        }
        backgroundPreferences = getSharedPreferences("background", MODE_PRIVATE);

        if(backgroundPreferences.getBoolean("background" ,true)) {
            mp.start();   // 노래 시작
            mp.setLooping(true);   // 반복 true 설정
        }
        super.onResume();
    }

    public void onPause() {
        mp.pause();
        super.onPause();
    }

}