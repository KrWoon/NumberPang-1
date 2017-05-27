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
    int Save[][] = new int[3][3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text1 = (TextView) findViewById(R.id.text1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.getText() == "") {
                    text1.setText(ranNumber + "");
                    Save[0][0] = ranNumber;
                    newRandom();
                }
            }
        });

        final TextView text2 = (TextView) findViewById(R.id.text2);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text2.getText() == "") {
                    text2.setText(ranNumber + "");
                    Save[0][1] = ranNumber;
                    newRandom();
                }
            }
        });

        final TextView text3 = (TextView) findViewById(R.id.text3);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text3.getText() == "") {
                    text3.setText(ranNumber + "");
                    Save[0][2] = ranNumber;
                    newRandom();
                }
            }
        });

        final TextView text4 = (TextView) findViewById(R.id.text4);
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text4.getText() == "") {
                    text4.setText(ranNumber + "");
                    Save[1][0] = ranNumber;
                    newRandom();
                }
            }
        });

        final TextView text5 = (TextView) findViewById(R.id.text5);
        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text5.getText() == "") {
                    text5.setText(ranNumber + "");
                    Save[1][1] = ranNumber;
                    newRandom();
                }
            }
        });

        final TextView text6 = (TextView) findViewById(R.id.text6);
        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text6.getText() == "") {
                    text6.setText(ranNumber + "");
                    Save[1][2] = ranNumber;
                    newRandom();
                }
            }
        });

        final TextView text7 = (TextView) findViewById(R.id.text7);
        text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text7.getText() == "") {
                    text7.setText(ranNumber + "");
                    Save[2][0] = ranNumber;
                    newRandom();
                }
            }
        });

        final TextView text8 = (TextView) findViewById(R.id.text8);
        text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text8.getText() == "") {
                    text8.setText(ranNumber + "");
                    Save[2][1] = ranNumber;
                    newRandom();
                }
            }
        });

        final TextView text9 = (TextView) findViewById(R.id.text9);
        text9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text9.getText() == "") {
                    text9.setText(ranNumber + "");
                    Save[2][2] = ranNumber;
                    newRandom();
                }
            }
        });
    }

    void newRandom() {
        Random r = new Random();
        this.ranNumber = r.nextInt(5) + 1;
    }


}