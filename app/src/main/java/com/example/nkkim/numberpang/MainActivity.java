package com.example.nkkim.numberpang;

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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text1 = (TextView) findViewById(R.id.text1);
        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText(ranNumber + "");
                newRandom();
            }
        });

        final TextView text2 = (TextView) findViewById(R.id.text2);
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text2.setText(ranNumber + "");
                newRandom();
            }
        });

        final TextView text3 = (TextView) findViewById(R.id.text3);
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text3.setText(ranNumber + "");
                newRandom();
            }
        });

        final TextView text4 = (TextView) findViewById(R.id.text4);
        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text4.setText(ranNumber + "");
                newRandom();
            }
        });

        final TextView text5 = (TextView) findViewById(R.id.text5);
        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text5.setText(ranNumber + "");
                newRandom();
            }
        });

        final TextView text6 = (TextView) findViewById(R.id.text6);
        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text6.setText(ranNumber + "");
                newRandom();
            }
        });

        final TextView text7 = (TextView) findViewById(R.id.text7);
        text7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text7.setText(ranNumber + "");
                newRandom();
            }
        });

        final TextView text8 = (TextView) findViewById(R.id.text8);
        text8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text8.setText(ranNumber + "");
                newRandom();
            }
        });

        final TextView text9 = (TextView) findViewById(R.id.text9);
        text9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text9.setText(ranNumber + "");
                newRandom();
            }
        });
    }

    void newRandom() {
        Random r = new Random();
        this.ranNumber = r.nextInt(5) + 1;
    }
}
