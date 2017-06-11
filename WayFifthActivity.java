package com.anjinma.numberpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WayFifthActivity extends AppCompatActivity implements View.OnClickListener {

    TextView fifthBack = null;
    TextView fifthGo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_fifth);
        fifthBack = (TextView)findViewById(R.id.Fifth_Back);
        fifthBack.setOnClickListener(this);
        fifthGo = (TextView)findViewById(R.id.Fifth_Go);
        fifthGo.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Fifth_Back:
                Intent intent = new Intent(getApplicationContext(), WayFourthActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.Fifth_Go:
                Intent intent2 = new Intent(getApplicationContext(), WaySixthActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }


}
