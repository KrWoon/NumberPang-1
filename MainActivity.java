package com.anjinma.numberpang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView startText = null;
    TextView optionText = null;
    TextView rankText = null;
    TextView wayText = null;
    TextView tierText = null;
    MediaPlayer mp = null;
    SharedPreferences backgroundPreferences = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startText = (TextView) findViewById(R.id.Start);
        startText.setOnClickListener(this);
        optionText = (TextView) findViewById(R.id.Option);
        optionText.setOnClickListener(this);
        rankText = (TextView) findViewById(R.id.Rank);
        rankText.setOnClickListener(this);
        wayText = (TextView) findViewById(R.id.Way);
        wayText.setOnClickListener(this);
        tierText = (TextView) findViewById((R.id.Tier));
        tierText.setOnClickListener(this);

    }



    public void onClick(View v) {
        mp.stop();   // 미디어 플레이어 중지
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                try{
                    mp.release();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mp, int what, int extra) {
                try {
                    mp.stop();
                    mp.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        switch(v.getId()) {
            case R.id.Start:
                Intent startIntent = new Intent(getApplicationContext(), ModeActivity.class);
                startActivity(startIntent);
                break;
            case R.id.Way:
                Intent wayIntent = new Intent(getApplicationContext(), WayFirstActivity.class);
                startActivity(wayIntent);
                break;
            case R.id.Tier:
                Intent teerIntent = new Intent(getApplicationContext(), TierInfiniteActivity.class);
                startActivity(teerIntent);
                break;
            case R.id.Option:
                Intent opIntent = new Intent(getApplicationContext(), OptionActivity.class);
                startActivity(opIntent);
                break;

            case R.id.Rank:
                Intent rankIntent = new Intent(getApplicationContext(), RankInfiniteActivity.class);
                startActivity(rankIntent);
                break;
        }
    }

    public void onResume() {
        mp = MediaPlayer.create(this, R.raw.mainbgm);

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

    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ExitActivity.class);
        startActivity(intent);
    }






}