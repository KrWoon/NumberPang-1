package com.anjinma.numberpang;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class ChallengeActivity extends AppCompatActivity implements View.OnClickListener{
    int ranNumber = 1;
    int saveData[][] = new int[3][3];       // 각 칸의 숫자 저장(데이터베이스)
    int saveRandom[] = new int[3];
    int score = 0;
    int combo = 0;
    int item = 1;
    int i, j;
    int time = 600;
    int comboStack = 0;
    boolean horizon[] = new boolean[3];
    boolean vertical[] = new boolean[3];
    boolean diagonal[] = new boolean[2];
    SQLiteDatabase db = null;
    SharedPreferences backgroundPreferences = null;
    SharedPreferences effectPreferences = null;

    public MediaPlayer mp = null;

    final int timeItem = 50;
    final int timeOne = 30;
    final int timeTwo = 20;
    final int timeThree = 50;
    final int timeFour = 200;
    final int scoreOne = 20;
    final int scoreTwo = 40;
    final int scoreThree = 100;
    final int scoreFour = 400;
    final int scoreMax = 99999;
    final int itemMax = 5;
    final int timeMax = 999;

    final TextView text[][] = new TextView[3][3];
    final int[][] textid = {{R.id.text1, R.id.text2, R.id.text3},
            {R.id.text4, R.id.text5, R.id.text6},
            {R.id.text7, R.id.text8, R.id.text9}};
    TextView randomText1 = null;
    TextView randomText2 = null;
    TextView randomText3 = null;
    TextView scoreText = null;
    TextView itemText = null;
    TextView timerText = null;

    CountDownTimer challengeTimer = new CountDownTimer(2147483647, 100) {

        public void onTick(long millisUntilFinished) {
            time--;
            if(time>=timeMax) {
                time = timeMax;
            }
            if(time == 0) {
                cancel();
                db = openOrCreateDatabase("scoreDB.db", MODE_PRIVATE, null);
                createTable("Challenge");
                insertData(score, getDate());
                Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
                intent.putExtra("TotalScore", "최종점수 : " + score + "점");
                intent.putExtra("modePAUSE", "challenge");
                startActivity(intent);
                finish();
            }
            timerText.setText((double)(time/10.0) + "초");
        }
        public void onFinish() {

        }
    };

    SoundPool soundpool;
    int soundId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        for(i=0; i<3; i++) {
            for(j=0; j<3; j++) {
                text[i][j] = (TextView) findViewById(textid[i][j]);
                text[i][j].setOnClickListener(this);
            }
        }

        randomText1 = (TextView) findViewById(R.id.Random1);
        randomText2 = (TextView) findViewById(R.id.Random2);
        randomText3 = (TextView) findViewById(R.id.Random3);

        timerText = (TextView) findViewById(R.id.Timer);
        scoreText = (TextView) findViewById(R.id.Score);
        itemText = (TextView) findViewById(R.id.Item);

        itemText.setOnClickListener(this);

        newRandom();
        randomText1.setText(ranNumber + "");
        saveRandom[0] = ranNumber;
        newRandom();
        randomText2.setText(ranNumber + "");
        saveRandom[1] = ranNumber;
        newRandom();
        randomText3.setText(ranNumber + "");
        saveRandom[2] = ranNumber;

        scoreText.setText(score + "");
        itemText.setText(item + "");

        if(soundpool == null) {
            soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            soundId = soundpool.load(this, R.raw.beep, 1);
        }
    }

    public void onClick(View v) {
        // 게임 기능 구현
        for(i=0; i<3; i++) {
            for(j=0; j<3; j++) {
                if (v.getId() == textid[i][j]) {
                    if (text[i][j].getText() == "") {
                        if(effectPreferences.getBoolean("effect", true))
                            soundpool.play(soundId, 1.0F, 1.0F,  1,  0,  1.0F); // soundId, leftVolum, rightVolum, priority, loop, rate

                        text[i][j].setText(saveRandom[0] + "");
                        saveData[i][j] = saveRandom[0];

                        randomText1.setText(randomText2.getText());
                        randomText2.setText(randomText3.getText());
                        newRandom();
                        randomText3.setText(ranNumber + "");
                        saveRandom[0] = saveRandom[1];
                        saveRandom[1] = saveRandom[2];
                        saveRandom[2] = ranNumber;
                        unLuckySeven();
                        crossPang();
                        checkCombo();

                        if (horizon[i] == true) {
                            text[i][0].setText("");  saveData[i][0] = 0;
                            text[i][1].setText("");  saveData[i][1] = 0;
                            text[i][2].setText("");  saveData[i][2] = 0;
                        }

                        if (vertical[j] == true) {
                            text[0][j].setText("");  saveData[0][j] = 0;
                            text[1][j].setText("");  saveData[1][j] = 0;
                            text[2][j].setText("");  saveData[2][j] = 0;
                        }

                        if (diagonal[0] == true) {
                            text[0][0].setText("");  saveData[0][0] = 0;
                            text[1][1].setText("");  saveData[1][1] = 0;
                            text[2][2].setText("");  saveData[2][2] = 0;
                        }

                        if (diagonal[1] == true) {
                            text[0][2].setText("");  saveData[0][2] = 0;
                            text[1][1].setText("");  saveData[1][1] = 0;
                            text[2][0].setText("");  saveData[2][0] = 0;
                        }

                        initBoolean();        // 콤보 검사한 값 초기화
                        checkGameover();      // 칸이 가득차면 게임오버
                    }
                }
            }
        }

        // 아이템 사용
        if(v.getId() == R.id.Item) {
            if (item > 0) {
                if(effectPreferences.getBoolean("effect", true))
                    soundpool.play(soundId, 1.0F, 1.0F,  1,  0,  1.0F); // soundId, leftVolum, rightVolum, priority, loop, rate
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        saveData[i][j] = 0;
                        text[i][j].setText("");
                    }
                }
                item--;
                itemText.setText(item + "");
                time = time + timeItem;
                Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, -100);
                toast.setView(getLayoutInflater().inflate(R.layout.clear, null));
                toast.show();
            }
        }
    }


    void newRandom() {
        Random r = new Random();
        this.ranNumber = r.nextInt(5) + 1;
    }

    void checkCombo() {
        // 가로의 합이 9면 true
        for (int i = 0; i < 3; i++) {
            if (saveData[i][0] + saveData[i][1] + saveData[i][2] == 9
                    && saveData[i][0] > 0 && saveData[i][1] > 0 && saveData[i][2] > 0) {
                horizon[i] = true;
                if(score<=scoreMax-scoreOne) {
                    score += scoreOne;
                }
                else {
                    score = scoreMax;
                }
                combo++;
                time = time + timeOne;
                comboStack++;
            }
        }

        //세로의 합이 9면 true
        for (int i = 0; i < 3; i++) {
            if (saveData[0][i] + saveData[1][i] + saveData[2][i] == 9
                    && saveData[0][i] > 0 && saveData[1][i] > 0 && saveData[2][i] > 0) {
                vertical[i] = true;
                if(score<=scoreMax-scoreOne) {
                    score += scoreOne;
                }
                else {
                    score = scoreMax;
                }
                combo++;
                time = time + timeOne;
                comboStack++;
            }
        }

        // 1 5 9 칸의 합이 9면 true
        if (saveData[0][0] + saveData[1][1] + saveData[2][2] == 9
                && saveData[0][0] > 0 && saveData[1][1] > 0 && saveData[2][2] > 0) {
            diagonal[0] = true;
            if(score<=scoreMax-scoreOne) {
                score += scoreOne;
            }
            else {
                score = scoreMax;
            }
            combo++;
            time = time + timeOne;
            comboStack++;
        }

        // 3 5 7 칸의 합이 9면 true
        if (saveData[2][0] + saveData[1][1] + saveData[0][2] == 9
                && saveData[0][2] > 0 && saveData[1][1] > 0 && saveData[2][0] > 0) {
            diagonal[1] = true;
            if(score<=scoreMax-scoreOne) {
                score += scoreOne;
            }
            else {
                score = scoreMax;
            }
            combo++;
            time = time + timeOne;
            comboStack++;
        }

        if (horizon[0] == false && horizon[1] == false && horizon[2] == false
                && vertical[0] == false && vertical[1] == false && vertical[2] == false
                && diagonal[0] == false && diagonal[1] == false) {
            combo = 0;
        }

        if(combo==2 && comboStack >= 1) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.twocombo, null));
            toast.show();
            if(item<itemMax) {
                item++;
            }
            time=time+timeTwo;
            if(score<=scoreMax-scoreTwo) {
                score += scoreTwo;
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==3 && comboStack == 1) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.threecombo, null));
            toast.show();
            if(item<itemMax) {
                item++;
            }
            time=time+timeThree;
            if(score<=scoreMax-scoreThree) {
                score += scoreThree;
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==3 && comboStack >= 2) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.threecombo, null));
            toast.show();
            if(item<itemMax) {
                item++;
            }
            if(item<itemMax) {
                item++;
            }
            time=time+timeThree+timeTwo;
            if(score<=scoreMax-(scoreThree+scoreTwo)) {
                score += (scoreThree+scoreTwo);
            }
            else {
                score = scoreMax;
            }
        }

        if(combo==4 && comboStack==1) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.fourcombo, null));
            toast.show();
            item = itemMax;
            time=time+timeFour;
            if(score<=scoreMax-scoreFour) {
                score += scoreFour;
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==4 && comboStack==2) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.fourcombo, null));
            toast.show();
            item = itemMax;
            time=time+timeFour+timeThree;
            if(score<=scoreMax-(scoreFour+scoreThree)) {
                score += (scoreFour+scoreThree);
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==4 && comboStack==3) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.fourcombo, null));
            toast.show();
            item = itemMax;
            time=time+timeFour+timeThree+timeTwo;
            if(score<=scoreMax-(scoreFour+scoreThree+scoreTwo)) {
                score += (scoreFour+scoreThree+scoreTwo);
            }
            else {
                score = scoreMax;
            }
        }

        if(combo==4 && comboStack==4) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.numberpang, null));
            toast.show();
            item = itemMax;
            time=time+timeFour+timeThree+timeTwo;
            if(score<=scoreMax-(1000-(4*scoreOne))) {
                score += 1000-(4*scoreOne);
            }
            else {
                score = scoreMax;
            }
        }

        comboStack = 0;
        itemText.setText(item + "");
        scoreText.setText(score + "");

    }

    void unLuckySeven() {
        int countOne = 0;
        int countTwo = 0;
        int countFour= 0;
        int countFive = 0;
        for(int i = 0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(saveData[i][j]==1) {
                    countOne++;
                }
                if(saveData[i][j]==2) {
                    countTwo++;
                }
                if(saveData[i][j]==4) {
                    countFour++;
                }
                if(saveData[i][j]==5) {
                    countFive++;
                }
            }
        }
        if(countOne ==7 || countTwo ==7 || countFour == 7 || countFive == 7) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.unluckyseven, null));
            toast.show();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    saveData[i][j] = 0;
                    text[i][j].setText("");
                }
            }
            if(score<=scoreMax-700) {
                score = score + 700;
            }
            else {
                score=scoreMax;
            }
            time = time + 70;

            if(item<=itemMax-1) {
                item++;
            }

        }
    }

    void crossPang() {
        int crossCheck = 0;
        if(saveData[0][1]==1 && saveData[1][0]==1 && saveData[1][1]==1 && saveData[1][2]==1 && saveData[2][1]==1 ) {
            saveData[0][1] =0;
            saveData[1][0] =0;
            saveData[1][1] =0;
            saveData[1][2] =0;
            saveData[2][1] =0;
            text[0][1].setText("");
            text[1][0].setText("");
            text[1][1].setText("");
            text[1][2].setText("");
            text[2][1].setText("");
            if (score<=scoreMax-500) {
                score = score + 500;
            }
            else {
                score = scoreMax;
            }
            time = time + 50;
            if(item<=itemMax-1) {
                item++;
            }
            crossCheck = 1;
        }
        if(saveData[0][1]==2 && saveData[1][0]==2 && saveData[1][1]==2 && saveData[1][2]==2 && saveData[2][1]==2 ) {
            saveData[0][1] =0;
            saveData[1][0] =0;
            saveData[1][1] =0;
            saveData[1][2] =0;
            saveData[2][1] =0;
            text[0][1].setText("");
            text[1][0].setText("");
            text[1][1].setText("");
            text[1][2].setText("");
            text[2][1].setText("");
            if (score<=scoreMax-500) {
                score = score + 500;
            }
            else {
                score = scoreMax;
            }
            time = time + 50;
            if(item<=itemMax-1) {
                item++;
            }
            crossCheck = 1;
        }
        if(saveData[0][1]==4 && saveData[1][0]==4 && saveData[1][1]==4 && saveData[1][2]==4 && saveData[2][1]==4 ) {
            saveData[0][1] =0;
            saveData[1][0] =0;
            saveData[1][1] =0;
            saveData[1][2] =0;
            saveData[2][1] =0;
            text[0][1].setText("");
            text[1][0].setText("");
            text[1][1].setText("");
            text[1][2].setText("");
            text[2][1].setText("");
            if (score<=scoreMax-500) {
                score = score + 500;
            }
            else {
                score = scoreMax;
            }
            time = time + 50;
            if(item<=itemMax-1) {
                item++;
            }
            crossCheck = 1;
        }
        if(saveData[0][1]==5 && saveData[1][0]==5 && saveData[1][1]==5 && saveData[1][2]==5 && saveData[2][1]==5 ) {
            saveData[0][1] =0;
            saveData[1][0] =0;
            saveData[1][1] =0;
            saveData[1][2] =0;
            saveData[2][1] =0;
            text[0][1].setText("");
            text[1][0].setText("");
            text[1][1].setText("");
            text[1][2].setText("");
            text[2][1].setText("");
            if (score<=scoreMax-500) {
                score = score + 500;
            }
            else {
                score = scoreMax;
            }
            time = time + 50;
            if(item<=itemMax-1) {
                item++;
            }
            crossCheck = 1;
        }

        if(crossCheck == 1) {
            Toast toast = Toast.makeText(getApplicationContext(), "Combo", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, -100);
            toast.setView(getLayoutInflater().inflate(R.layout.crosspang, null));
            toast.show();
        }

    }

    void initBoolean() {
        for (int i = 0; i < 3; i++) {
            horizon[i] = false;
            vertical[i] = false;
        }
        diagonal[0] = false;
        diagonal[1] = false;
    }



    public void pauseClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), PauseActivity.class);
        intent.putExtra("modePAUSE", "challenge");
        startActivity(intent);
    }

    public void optionClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), OptionActivity.class);
        startActivity(intent);
    }

    public void wayClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), WayFirstActivity.class);
        startActivity(intent);
    }

    public void rankClicked(View v) {
        Intent intent = new Intent(getApplicationContext(), RankChallengeActivity.class);
        startActivity(intent);
    }

    void checkGameover() {      // 칸이 가득차면 게임오버
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (saveData[i][j] > 0) {
                    count++;
                }
            }
        }

        if (count == 9) {
            challengeTimer.cancel();
            db = openOrCreateDatabase("scoreDB.db", MODE_PRIVATE, null);
            createTable("Challenge");
            insertData(score, getDate());
            Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
            intent.putExtra("TotalScore", "최종점수\n"+score+"점");
            intent.putExtra("modePAUSE", "challenge");
            startActivity(intent);
            finish();
        }
    }

    public void onUserLeaveHint() {
        challengeTimer.cancel();
    }

    public void onResume() {
        if(mp == null) {
            mp = MediaPlayer.create(this, R.raw.gamebgm);
        }
        backgroundPreferences = getSharedPreferences("background", MODE_PRIVATE);
        effectPreferences = getSharedPreferences("effect", MODE_PRIVATE);

        if(backgroundPreferences.getBoolean("background" ,true)) {
            mp.start();   // 노래 시작
            mp.setLooping(true);   // 반복 true 설정
        }
        time++;
        challengeTimer.start();
        super.onResume();
    }

    public void onPause() {
        if(mp != null) {
            mp.pause();
        }
        challengeTimer.cancel();
        super.onPause();
    }

    public void onBackPressed() {
        challengeTimer.cancel();
        Intent intent = new Intent(getApplicationContext(), PauseActivity.class);
        intent.putExtra("modePAUSE", "challenge");
        startActivity(intent);
    }

    String getDate() {
        String date;
        Calendar now = Calendar.getInstance();

        date = now.get(Calendar.YEAR) + "." + now.get(Calendar.MONTH) + "." + now.get(Calendar.DATE) + " " + now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);

        return date;
    }

    private void createDatabase() {
        String name = "scoreDB.db";
        db = SQLiteDatabase.openOrCreateDatabase(name,  null);
    }

    private void createTable(String tableName) {

        String sql = "create table " + tableName + " (score int, date text)";

        try {
            db.execSQL(sql);//slq문 실행
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "Create Table 오류", Toast.LENGTH_LONG).show();
        }
    }

    public void insertData(int score, String date) {
        db.beginTransaction(); //sql문을 실행하는 일정구간을 트랜잭션으로 묶어주겠다라는 의미
        //트랜잭션 시작을 나타내는 메소드
        try {

            String sql = "insert into Challenge (score, date) values(" + String.valueOf(score) + ", '" + date + "')";
            db.execSQL(sql);

            db.setTransactionSuccessful(); //트랜잭션으로 묶어준 일정영역의 SQL문들이 모두 성공적으로 끝났을 지정

        } catch (Exception e) {
            //SQL문 실행중 오류가 발생하면 예외처리가 되고..
            //트랜잭션에 정의된 SQL쿼리가 성공되지 않았기때문에 finally블록에서
            //트랜잭션 종료메서드 실행시(endTransaction()) 롤백이 된다.

            //Toast.makeText(getApplicationContext(), "DB 오류", Toast.LENGTH_LONG).show();
        } finally {
            db.endTransaction(); //트랜잭션을 끝내는 메소드.
        }
    }
}