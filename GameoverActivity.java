package com.example.numberpang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 박지운 on 2017-05-29.
 * 게임종료 액티비티
 */

public class GameoverActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover_main);

        MainActivity mainAct = new MainActivity();
        TextView totalScore = (TextView) findViewById(R.id.TotalScore);
        totalScore.setText(mainAct.score + "");


        Button backButton = (Button) findViewById((R.id.BackButton));
        backButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                finish();
            }
        });
    }
}
