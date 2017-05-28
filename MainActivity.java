package com.kkh.numberpang;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int ranNumber = 1;
    int Save[][] = new int[3][3];       // 각 칸의 숫자 저장(데이터베이스)
    int saveRandom[] = new int[3];
    int score = 0;
    int combo = -1;
    int item = 1;
    int i, j;
    int time = 61;
    boolean horizon[] = new boolean[3];
    boolean vertical[] = new boolean[3];
    boolean diagonal[] = new boolean[2];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text1 = (TextView) findViewById(R.id.text1);
        final TextView text2 = (TextView) findViewById(R.id.text2);
        final TextView text3 = (TextView) findViewById(R.id.text3);
        final TextView text4 = (TextView) findViewById(R.id.text4);
        final TextView text5 = (TextView) findViewById(R.id.text5);
        final TextView text6 = (TextView) findViewById(R.id.text6);
        final TextView text7 = (TextView) findViewById(R.id.text7);
        final TextView text8 = (TextView) findViewById(R.id.text8);
        final TextView text9 = (TextView) findViewById(R.id.text9);

        final TextView Random1 = (TextView) findViewById(R.id.Random1);
        final TextView Random2 = (TextView) findViewById(R.id.Random2);
        final TextView Random3 = (TextView) findViewById(R.id.Random3);

        final TextView Score = (TextView) findViewById(R.id.Score);
        final TextView Item = (TextView) findViewById(R.id.Item);
        final TextView temp = (TextView) findViewById(R.id.temp);
        final TextView textTimer = (TextView) findViewById(R.id.textTimer);


        newRandom();
        Random1.setText(ranNumber + "");
        saveRandom[0] = ranNumber;
        newRandom();
        Random2.setText(ranNumber + "");
        saveRandom[1] = ranNumber;
        newRandom();
        Random3.setText(ranNumber + "");
        saveRandom[2] = ranNumber;

        Score.setText(score + "");
        Item.setText(item + "");


        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text1.getText() == "") {
                    text1.setText(saveRandom[0] + "");   // 칸에 값 입력
                    Save[0][0] = saveRandom[0];          // Save 데이터에 값 저장

                    Random1.setText(Random2.getText());     // 미리 보여주기 숫자 이동
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;

                    checkSave();                             // 콤보가 있는지 검사
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text2.setText("");
                        Save[0][1] = 0;
                        text3.setText("");
                        Save[0][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text4.setText("");
                        Save[1][0] = 0;
                        text7.setText("");
                        Save[2][0] = 0;
                        Score.setText(score + "");
                    }

                    if (diagonal[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();      // 칸이 가득차면 게임오버
                    initBoolean();        // 콤보 검사한 값 초기화
                }
            }
        });


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text2.getText() == "") {
                    text2.setText(saveRandom[0] + "");
                    Save[0][1] = saveRandom[0];

                    Random1.setText(Random2.getText());
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;
                    checkSave();
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text2.setText("");
                        Save[0][1] = 0;
                        text3.setText("");
                        Save[0][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[1] == true) {
                        text2.setText("");
                        Save[0][1] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text8.setText("");
                        Save[2][1] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();
                    initBoolean();
                }
            }
        });


        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text3.getText() == "") {
                    text3.setText(saveRandom[0] + "");
                    Save[0][2] = saveRandom[0];

                    Random1.setText(Random2.getText());
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;
                    checkSave();
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text2.setText("");
                        Save[0][1] = 0;
                        text3.setText("");
                        Save[0][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[2] == true) {
                        text3.setText("");
                        Save[0][2] = 0;
                        text6.setText("");
                        Save[1][2] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    if (diagonal[1] == true) {
                        text3.setText("");
                        Save[0][2] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text7.setText("");
                        Save[2][0] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();
                    initBoolean();
                }
            }
        });


        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text4.getText() == "") {
                    text4.setText(saveRandom[0] + "");
                    Save[1][0] = saveRandom[0];

                    Random1.setText(Random2.getText());
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;
                    checkSave();
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[1] == true) {
                        text4.setText("");
                        Save[1][0] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text6.setText("");
                        Save[1][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text4.setText("");
                        Save[1][0] = 0;
                        text7.setText("");
                        Save[2][0] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();
                    initBoolean();
                }
            }
        });


        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text5.getText() == "") {
                    text5.setText(saveRandom[0] + "");
                    Save[1][1] = saveRandom[0];

                    Random1.setText(Random2.getText());
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;
                    checkSave();
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[1] == true) {
                        text4.setText("");
                        Save[1][0] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text6.setText("");
                        Save[1][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[1] == true) {
                        text2.setText("");
                        Save[0][1] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text8.setText("");
                        Save[2][1] = 0;
                        Score.setText(score + "");
                    }

                    if (diagonal[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    if (diagonal[1] == true) {
                        text3.setText("");
                        Save[0][2] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text7.setText("");
                        Save[2][0] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();
                    initBoolean();
                }
            }
        });


        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text6.getText() == "") {
                    text6.setText(saveRandom[0] + "");
                    Save[1][2] = saveRandom[0];

                    Random1.setText(Random2.getText());
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;
                    checkSave();
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[1] == true) {
                        text4.setText("");
                        Save[1][0] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text6.setText("");
                        Save[1][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[2] == true) {
                        text3.setText("");
                        Save[0][2] = 0;
                        text6.setText("");
                        Save[1][2] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();
                    initBoolean();
                }
            }
        });


        text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text7.getText() == "") {
                    text7.setText(saveRandom[0] + "");
                    Save[2][0] = saveRandom[0];

                    Random1.setText(Random2.getText());
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;
                    checkSave();
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[2] == true) {
                        text7.setText("");
                        Save[2][0] = 0;
                        text8.setText("");
                        Save[2][1] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text4.setText("");
                        Save[1][0] = 0;
                        text7.setText("");
                        Save[2][0] = 0;
                        Score.setText(score + "");
                    }

                    if (diagonal[1] == true) {
                        text3.setText("");
                        Save[0][2] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text7.setText("");
                        Save[2][0] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();
                    initBoolean();
                }
            }
        });


        text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text8.getText() == "") {
                    text8.setText(saveRandom[0] + "");
                    Save[2][1] = saveRandom[0];

                    Random1.setText(Random2.getText());
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;
                    checkSave();
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[2] == true) {
                        text7.setText("");
                        Save[2][0] = 0;
                        text8.setText("");
                        Save[2][1] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[1] == true) {
                        text2.setText("");
                        Save[0][1] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text8.setText("");
                        Save[2][1] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();
                    initBoolean();
                }
            }
        });


        text9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text9.getText() == "") {
                    text9.setText(saveRandom[0] + "");
                    Save[2][2] = saveRandom[0];

                    Random1.setText(Random2.getText());
                    Random2.setText(Random3.getText());
                    newRandom();
                    Random3.setText(ranNumber + "");
                    saveRandom[0] = saveRandom[1];
                    saveRandom[1] = saveRandom[2];
                    saveRandom[2] = ranNumber;
                    checkSave();
                    Item.setText(item + "");
                    textTimer.setText(time + "초");
                    if (horizon[2] == true) {
                        text7.setText("");
                        Save[2][0] = 0;
                        text8.setText("");
                        Save[2][1] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    if (vertical[2] == true) {
                        text3.setText("");
                        Save[0][2] = 0;
                        text6.setText("");
                        Save[1][2] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    if (diagonal[0] == true) {
                        text1.setText("");
                        Save[0][0] = 0;
                        text5.setText("");
                        Save[1][1] = 0;
                        text9.setText("");
                        Save[2][2] = 0;
                        Score.setText(score + "");
                    }

                    checkGameover();
                    initBoolean();
                }
            }
        });

        Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item > 0) {
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            Save[i][j] = 0;
                        }
                    }

                    text1.setText("");
                    text2.setText("");
                    text3.setText("");
                    text4.setText("");
                    text5.setText("");
                    text6.setText("");
                    text7.setText("");
                    text8.setText("");
                    text9.setText("");

                    item--;
                    Item.setText(item + "");
                    time = time + 5;
                    textTimer.setText(time + "초");
                    Toast.makeText(getApplicationContext(), "아이템 사용 !", Toast.LENGTH_SHORT).show();
                }
            }

        });

        temp.setOnClickListener(new View.OnClickListener() {                                            //게임 재시작 텍스트 버튼
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "게임 재시작", Toast.LENGTH_SHORT).show();
                Intent reIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(reIntent);
                finish();
            }

        });

    }


    void newRandom() {
        Random r = new Random();
        this.ranNumber = r.nextInt(5) + 1;

    }

    void checkSave() {
        // 가로의 합이 9면 true
        for (int i = 0; i < 3; i++) {
            if (Save[i][0] + Save[i][1] + Save[i][2] == 9 && Save[i][0] > 0 && Save[i][1] > 0 && Save[i][2] > 0) {
                horizon[i] = true;
                score += 10;
                combo++;
                time = time + 3;
            }
        }
        //세로의 합이 9면 true
        for (int i = 0; i < 3; i++) {
            if (Save[0][i] + Save[1][i] + Save[2][i] == 9 && Save[0][i] > 0 && Save[1][i] > 0 && Save[2][i] > 0) {
                vertical[i] = true;
                score += 10;
                combo++;
                time = time + 3;
            }
        }
        // 1 5 9 칸의 합이 9면 true
        if (Save[0][0] + Save[1][1] + Save[2][2] == 9 && Save[0][0] > 0 && Save[1][1] > 0 && Save[2][2] > 0) {
            diagonal[0] = true;
            score += 10;
            combo++;
            time = time + 3;
        }

        // 3 5 7 칸의 합이 9면 true
        if (Save[2][0] + Save[1][1] + Save[0][2] == 9 && Save[0][2] > 0 && Save[1][1] > 0 && Save[2][0] > 0) {
            diagonal[1] = true;
            score += 10;
            combo++;
            time = time + 3;
        }

        if (horizon[0] == false && horizon[1] == false && horizon[2] == false && vertical[0] == false && vertical[1] == false && vertical[2] == false && diagonal[0] == false && diagonal[1] == false) {
            combo = -1;
        }

        while (combo >= 1) {                   //1콤보 이상이면 아이템 1개씩 증가
            Toast.makeText(getApplicationContext(), combo + " Combo !", Toast.LENGTH_SHORT).show();
            item++;
            time = time + combo * combo;
            score = score + 10 * combo * combo;
            combo--;
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

    void RestartClicked(View v) {                                                                      //게임 재시작
        Toast.makeText(getApplicationContext(), "게임 재시작", Toast.LENGTH_SHORT).show();
        Intent reIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(reIntent);
        finish();
    }

    void startClicked(View v) {                                                                      //게임 재시작
        Toast.makeText(getApplicationContext(), "게임 시작", Toast.LENGTH_SHORT).show();
        final TextView textTimer = (TextView) findViewById(R.id.textTimer);
        new CountDownTimer(2147483647, 1000) {

            public void onTick(long millisUntilFinished) {
                time--;
                textTimer.setText(time + "초");
                if (time == 0) {
                    cancel();
                    Toast.makeText(getApplicationContext(), "최종점수는 " + score + "입니다", Toast.LENGTH_LONG).show();
                    Intent reIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(reIntent);
                    finish();
                }
            }

            public void onFinish() {

            }
        }.start();
    }

    void checkGameover() {      // 칸이 가득차면 게임오버
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Save[i][j] > 0) {
                    count++;
                }
            }
        }

        if (count == 9) {
            Toast.makeText(getApplicationContext(), "최종점수는 " + score + "입니다", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), GameoverActivity.class);

            intent.putExtra("TotalScore", score+"");
            //this.setResult(this.RESULT_OK,intent);
            startActivity(intent);
        }
    }
}