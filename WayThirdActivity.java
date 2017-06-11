package com.anjinma.numberpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WayThirdActivity extends AppCompatActivity implements View.OnClickListener {

    TextView thirdBack = null;
    TextView thirdGo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_third);
        thirdBack = (TextView)findViewById(R.id.Third_Back);
        thirdBack.setOnClickListener(this);
        thirdGo = (TextView)findViewById(R.id.Third_Go);
        thirdGo.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Third_Back:
                Intent intent = new Intent(getApplicationContext(), WaySecondActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.Third_Go:
                Intent intent2 = new Intent(getApplicationContext(), WayFourthActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }


}
