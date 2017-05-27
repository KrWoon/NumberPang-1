package com.example.numberpang;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import static android.R.id.text1;

public class MainActivity extends AppCompatActivity {
    int ranNumber = 1;
    int Save[][] = new int[3][3];       // 각 칸의 숫자 저장(데이터베이스)
    int saveRandom[] = new int[3];
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
        newRandom();
        Random1.setText(ranNumber + "");
        saveRandom[0] = ranNumber;

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.getText() == "") {
                    text1.setText(saveRandom[0] + "");
                    Save[0][0] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;

                    checkSave();

                    if(horizon[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text2.setText(""); Save[0][1] = 0;
                        text3.setText(""); Save[0][2] = 0;
                    }

                    if(vertical[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text4.setText(""); Save[1][0] = 0;
                        text7.setText(""); Save[2][0] = 0;
                    }

                    if(diagonal[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text9.setText(""); Save[2][2] = 0;
                    }

                    initBoolean();
                }
            }
        });


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text2.getText() == "") {
                    text2.setText(saveRandom[0] + "");
                    Save[0][1] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;
                    checkSave();

                    if(horizon[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text2.setText(""); Save[0][1] = 0;
                        text3.setText(""); Save[0][2] = 0;
                    }

                    if(vertical[1] == true) {
                        text2.setText(""); Save[0][1] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text8.setText(""); Save[2][1] = 0;
                    }

                    initBoolean();
                }
            }
        });


        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text3.getText() == "") {
                    text3.setText(saveRandom[0] + "");
                    Save[0][2] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;
                    checkSave();

                    if(horizon[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text2.setText(""); Save[0][1] = 0;
                        text3.setText(""); Save[0][2] = 0;
                    }

                    if(vertical[2] == true) {
                        text3.setText(""); Save[0][2] = 0;
                        text6.setText(""); Save[1][2] = 0;
                        text9.setText(""); Save[2][2] = 0;
                    }

                    if(diagonal[1] == true) {
                        text3.setText(""); Save[0][2] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text7.setText(""); Save[2][0] = 0;
                    }

                    initBoolean();
                }
            }
        });


        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text4.getText() == "") {
                    text4.setText(saveRandom[0] + "");
                    Save[1][0] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;
                    checkSave();

                    if(horizon[1] == true) {
                        text4.setText(""); Save[1][0] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text6.setText(""); Save[1][2] = 0;
                    }

                    if(vertical[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text4.setText(""); Save[1][0] = 0;
                        text7.setText(""); Save[2][0] = 0;
                    }

                    initBoolean();
                }
            }
        });


        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text5.getText() == "") {
                    text5.setText(saveRandom[0] + "");
                    Save[1][1] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;
                    checkSave();

                    if(horizon[1] == true) {
                        text4.setText(""); Save[1][0] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text6.setText(""); Save[1][2] = 0;
                    }

                    if(vertical[1] == true) {
                        text2.setText(""); Save[0][1] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text8.setText(""); Save[2][1] = 0;
                    }

                    if(diagonal[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text9.setText(""); Save[2][2] = 0;
                    }

                    if(diagonal[1] == true) {
                        text3.setText(""); Save[0][2] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text7.setText(""); Save[2][0] = 0;
                    }

                    initBoolean();
                }
            }
        });


        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text6.getText() == "") {
                    text6.setText(saveRandom[0] + "");
                    Save[1][2] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;
                    checkSave();

                    if(horizon[1] == true) {
                        text4.setText(""); Save[1][0] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text6.setText(""); Save[1][2] = 0;
                    }

                    if(vertical[2] == true) {
                        text3.setText(""); Save[0][2] = 0;
                        text6.setText(""); Save[1][2] = 0;
                        text9.setText(""); Save[2][2] = 0;
                    }

                    initBoolean();
                }
            }
        });


        text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text7.getText() == "") {
                    text7.setText(saveRandom[0] + "");
                    Save[2][0] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;
                    checkSave();

                    if(horizon[2] == true) {
                        text7.setText(""); Save[1][0] = 0;
                        text8.setText(""); Save[1][1] = 0;
                        text9.setText(""); Save[1][2] = 0;
                    }

                    if(vertical[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text4.setText(""); Save[1][0] = 0;
                        text7.setText(""); Save[2][0] = 0;
                    }

                    if(diagonal[1] == true) {
                        text3.setText(""); Save[0][2] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text7.setText(""); Save[2][0] = 0;
                    }

                    initBoolean();
                }
            }
        });


        text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text8.getText() == "") {
                    text8.setText(saveRandom[0] + "");
                    Save[2][1] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;
                    checkSave();

                    if(horizon[2] == true) {
                        text7.setText(""); Save[1][0] = 0;
                        text8.setText(""); Save[1][1] = 0;
                        text9.setText(""); Save[1][2] = 0;
                    }

                    if(vertical[1] == true) {
                        text2.setText(""); Save[0][1] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text8.setText(""); Save[2][1] = 0;
                    }

                    initBoolean();
                }
            }
        });


        text9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text9.getText() == "") {
                    text9.setText(saveRandom[0] + "");
                    Save[2][2] = saveRandom[0];

                    newRandom();
                    Random1.setText(ranNumber + "");
                    saveRandom[0] = ranNumber;
                    checkSave();

                    if(horizon[2] == true) {
                        text7.setText(""); Save[1][0] = 0;
                        text8.setText(""); Save[1][1] = 0;
                        text9.setText(""); Save[1][2] = 0;
                    }

                    if(vertical[2] == true) {
                        text3.setText(""); Save[0][2] = 0;
                        text6.setText(""); Save[1][2] = 0;
                        text9.setText(""); Save[2][2] = 0;
                    }

                    if(diagonal[0] == true) {
                        text1.setText(""); Save[0][0] = 0;
                        text5.setText(""); Save[1][1] = 0;
                        text9.setText(""); Save[2][2] = 0;
                    }

                    initBoolean();
                }
            }
        });
    }

    void newRandom() {
        Random r = new Random();
        this.ranNumber = r.nextInt(5) + 1;
    }

    void checkSave() {
        // 가로의 합이 9면 true
        for(int i=0; i<3; i++)
            if(Save[i][0] + Save[i][1] + Save[i][2] == 9 && Save[i][0] > 0 && Save[i][1] > 0 && Save[i][2] > 0)
                horizon[i] = true;

        //세로의 합이 9면 true
        for(int i=0; i<3; i++)
            if(Save[0][i] + Save[1][i] + Save[2][i] == 9 && Save[0][i] > 0 && Save[1][i] > 0 && Save[2][i] > 0)
                vertical[i] = true;

        // 1 5 9 칸의 합이 9면 true
        if(Save[0][0] + Save[1][1] + Save[2][2] == 9 && Save[0][0] > 0 && Save[1][1] > 0 && Save[2][2] > 0)
            diagonal[0] = true;

        // 3 5 7 칸의 합이 9면 true
        if(Save[2][0] + Save[1][1] + Save[0][2] == 9 && Save[0][2] > 0 && Save[1][1] > 0 && Save[2][0] > 0)
            diagonal[0] = true;
    }

    void initBoolean() {
        for(int i=0;i<3;i++) {
            horizon[i] = false;
            vertical[i] = false;
        }
        diagonal[0] = false;
        diagonal[1] = false;
    }


}