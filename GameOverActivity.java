package com.anjinma.numberpang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by 박지운 on 2017-05-29.
 * 게임종료 액티비티
 */

public class GameOverActivity extends Activity implements View.OnClickListener {
    TextView totalScore = null;
    TextView gameoverMainmenu = null;
    TextView gameoverRestart = null;
    String mode = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        totalScore = (TextView) findViewById(R.id.TotalScore);
        gameoverMainmenu = (TextView) findViewById(R.id.gameoverMainmenu);
        gameoverRestart = (TextView) findViewById(R.id.gameoverRestart);
        totalScore.setOnClickListener(this);
        gameoverMainmenu.setOnClickListener(this);
        gameoverRestart.setOnClickListener(this);

        Intent intent = getIntent();
        String score = intent.getStringExtra("TotalScore");
        totalScore.setText(score);

        Intent modeIntent = getIntent();
        mode = modeIntent.getStringExtra("modePAUSE");
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.gameoverRestart:

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

            case R.id.gameoverMainmenu:
                Intent mainmenuIntent = new Intent(getApplicationContext(), MainActivity.class);
                mainmenuIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(mainmenuIntent);
                finish();
                break;
        }
    }
}