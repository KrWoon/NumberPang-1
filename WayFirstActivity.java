package com.anjinma.numberpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WayFirstActivity extends AppCompatActivity implements View.OnClickListener {

    TextView firstGo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_first);

        firstGo = (TextView)findViewById(R.id.First_Go);
        firstGo.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.First_Go:
                Intent intent2 = new Intent(getApplicationContext(), WaySecondActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }


}
