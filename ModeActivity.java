package com.anjinma.numberpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.anjinma.numberpang.R.id.Infinite;

public class ModeActivity extends AppCompatActivity implements View.OnClickListener{
    TextView infiniteText = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        infiniteText = (TextView) findViewById(R.id.Infinite);
        infiniteText.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Infinite:
                Toast.makeText(getApplicationContext(), "Infinite Mode Start", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), InfiniteActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
