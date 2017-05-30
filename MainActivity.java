package com.anjinma.numberpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.anjinma.numberpang.InfiniteActivity;

import static com.anjinma.numberpang.R.id.Start;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView startText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startText = (TextView) findViewById(R.id.Start);
        startText.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Start:
                Toast.makeText(getApplicationContext(), "게임 시작", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ModeActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
