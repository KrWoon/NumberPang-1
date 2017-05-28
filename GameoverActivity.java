package com.kkh.numberpang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.kkh.numberpang.R.id.TotalScore;

/**
 * Created by 박지운 on 2017-05-29.
 * 게임종료 액티비티
 */

public class GameoverActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        Intent intent = getIntent();
        String score = intent.getStringExtra("TotalScore");

        MainActivity mainAct = new MainActivity();
        TextView totalScore = (TextView) findViewById(TotalScore);
        totalScore.setText("최종점수 : " + score);


        Button backButton = (Button) findViewById((R.id.BackButton));
        backButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                finish();
            }
        });
    }
}