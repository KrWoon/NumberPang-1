package com.anjinma.numberpang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class InfiniteActivity extends AppCompatActivity implements View.OnClickListener{
    int ranNumber = 1;
    int saveData[][] = new int[3][3];       // 각 칸의 숫자 저장(데이터베이스)
    int saveRandom[] = new int[3];
    int score = 0;
    int combo = 0;
    int item = 1;
    int i, j;
    int comboStack = 0;
    boolean horizon[] = new boolean[3];
    boolean vertical[] = new boolean[3];
    boolean diagonal[] = new boolean[2];

    final int scoreMax = 99999;
    final int itemMax = 99;

    final TextView text[][] = new TextView[3][3];
    final int[][] textid = {{R.id.text1, R.id.text2, R.id.text3},
            {R.id.text4, R.id.text5, R.id.text6},
            {R.id.text7, R.id.text8, R.id.text9}};
    TextView randomText1 = null;
    TextView randomText2 = null;
    TextView randomText3 = null;
    TextView scoreText = null;
    TextView itemText = null;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite);

        for(i=0; i<3; i++) {
            for(j=0; j<3; j++) {
                text[i][j] = (TextView) findViewById(textid[i][j]);
                text[i][j].setOnClickListener(this);
            }
        }

        randomText1 = (TextView) findViewById(R.id.Random1);
        randomText2 = (TextView) findViewById(R.id.Random2);
        randomText3 = (TextView) findViewById(R.id.Random3);

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
    }

    public void onClick(View v) {
        // 게임 기능 구현
        for(i=0; i<3; i++) {
            for(j=0; j<3; j++) {
                if (v.getId() == textid[i][j]) {
                    if (text[i][j].getText() == "") {
                        text[i][j].setText(saveRandom[0] + "");
                        saveData[i][j] = saveRandom[0];

                        randomText1.setText(randomText2.getText());
                        randomText2.setText(randomText3.getText());
                        newRandom();
                        randomText3.setText(ranNumber + "");
                        saveRandom[0] = saveRandom[1];
                        saveRandom[1] = saveRandom[2];
                        saveRandom[2] = ranNumber;

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
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        saveData[i][j] = 0;
                        text[i][j].setText("");
                    }
                }
                item--;
                itemText.setText(item + "");

                Toast.makeText(getApplicationContext(), "아이템 사용 !", Toast.LENGTH_SHORT).show();
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
                if(score<=scoreMax-10) {
                    score += 10;
                }
                else {
                    score = scoreMax;
                }
                combo++;

                comboStack++;
            }
        }

        //세로의 합이 9면 true
        for (int i = 0; i < 3; i++) {
            if (saveData[0][i] + saveData[1][i] + saveData[2][i] == 9
                    && saveData[0][i] > 0 && saveData[1][i] > 0 && saveData[2][i] > 0) {
                vertical[i] = true;
                if(score<=scoreMax-10) {
                    score += 10;
                }
                else {
                    score = scoreMax;
                }
                combo++;

                comboStack++;
            }
        }

        // 1 5 9 칸의 합이 9면 true
        if (saveData[0][0] + saveData[1][1] + saveData[2][2] == 9
                && saveData[0][0] > 0 && saveData[1][1] > 0 && saveData[2][2] > 0) {
            diagonal[0] = true;
            if(score<=scoreMax-10) {
                score += 10;
            }
            else {
                score = scoreMax;
            }
            combo++;

            comboStack++;
        }

        // 3 5 7 칸의 합이 9면 true
        if (saveData[2][0] + saveData[1][1] + saveData[0][2] == 9
                && saveData[0][2] > 0 && saveData[1][1] > 0 && saveData[2][0] > 0) {
            diagonal[1] = true;
            if(score<=scoreMax-10) {
                score += 10;
            }
            else {
                score = scoreMax;
            }
            combo++;

            comboStack++;
        }

        if (horizon[0] == false && horizon[1] == false && horizon[2] == false
                && vertical[0] == false && vertical[1] == false && vertical[2] == false
                && diagonal[0] == false && diagonal[1] == false) {
            combo = 0;
        }

        if(combo==2 && comboStack >= 1) {
            Toast.makeText(getApplicationContext(), combo + " Combo !" , Toast.LENGTH_SHORT).show();
            if(item<=itemMax-1) {
                item++;
            }

            if(score<=scoreMax-20) {
                score += 20;
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==3 && comboStack == 1) {
            Toast.makeText(getApplicationContext(), combo + " Combo !" , Toast.LENGTH_SHORT).show();
            if(item<=itemMax-1) {
                item++;
            }

            if(score<=scoreMax-50) {
                score += 50;
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==3 && comboStack >= 2) {
            Toast.makeText(getApplicationContext(), combo + " Combo !" , Toast.LENGTH_SHORT).show();
            if(item<=itemMax-2) {
                item = item + 2;
            }
            else {
                item = itemMax;
            }

            if(score<=scoreMax-70) {
                score += 70;
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==4 && comboStack ==1) {
            Toast.makeText(getApplicationContext(), combo + " Combo !" , Toast.LENGTH_SHORT).show();
            if(item<=itemMax-5) {
                item = item + 5;
            }
            else {
                item = itemMax;
            }

            if(score<=scoreMax-500) {
                score += 500;
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==4 && comboStack ==2) {
            Toast.makeText(getApplicationContext(), combo + " Combo !" , Toast.LENGTH_SHORT).show();
            if(item<=itemMax-6) {
                item = item + 6;
            }
            else {
                item = itemMax;
            }

            if(score<=scoreMax-550) {
                score += 550;
            }
            else {
                score = scoreMax;
            }
        }
        if(combo==4 && comboStack >=3) {
            Toast.makeText(getApplicationContext(), combo + " Combo !" , Toast.LENGTH_SHORT).show();
            if(item<=itemMax-7) {
                item = item + 7;
            }
            else {
                item = itemMax;
            }
            if(score<=scoreMax-570) {
                score += 570;
            }
            else {
                score = scoreMax;
            }
        }


        comboStack = 0;
        itemText.setText(item + "");
        scoreText.setText(score + "");

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
        Toast.makeText(getApplicationContext(), "일시 정지", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), PauseActivity.class);
        intent.putExtra("modePAUSE", "infinite");
        startActivity(intent);
    }

    public void optionClicked(View v) {
        Toast.makeText(getApplicationContext(), "옵션", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), OptionActivity.class);
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
            Toast.makeText(getApplicationContext(), "최종점수는 " + score + "점 입니다", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), GameOverActivity.class);
            intent.putExtra("TotalScore", "최종점수\n"+score+"점");
            intent.putExtra("modePAUSE", "infinite");
            startActivity(intent);
            finish();
        }
    }



    public void onBackPressed() {

        Toast.makeText(getApplicationContext(), "일시 정지", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), PauseActivity.class);
        intent.putExtra("modePAUSE", "infinite");
        startActivity(intent);
    }
}