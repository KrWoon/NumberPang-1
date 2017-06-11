package com.anjinma.numberpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WayFourthActivity extends AppCompatActivity implements View.OnClickListener {

    TextView fourthBack = null;
    TextView fourthGo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_fourth);
        fourthBack = (TextView)findViewById(R.id.Fourth_Back);
        fourthBack.setOnClickListener(this);
        fourthGo = (TextView)findViewById(R.id.Fourth_Go);
        fourthGo.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Fourth_Back:
                Intent intent = new Intent(getApplicationContext(), WayThirdActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.Fourth_Go:
                Intent intent2 = new Intent(getApplicationContext(), WayFifthActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }


}
