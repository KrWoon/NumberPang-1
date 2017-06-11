package com.anjinma.numberpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WaySecondActivity extends AppCompatActivity implements View.OnClickListener {

    TextView secondBack = null;
    TextView secondGo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_second);
        secondBack = (TextView)findViewById(R.id.Second_Back);
        secondBack.setOnClickListener(this);
        secondGo = (TextView)findViewById(R.id.Second_Go);
        secondGo.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Second_Back:
                Intent intent = new Intent(getApplicationContext(), WayFirstActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.Second_Go:
                Intent intent2 = new Intent(getApplicationContext(), WayThirdActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }


}
