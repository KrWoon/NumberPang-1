package com.anjinma.numberpang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class WaySixthActivity extends AppCompatActivity implements View.OnClickListener {

    TextView sixthBack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way_sixth);
        sixthBack = (TextView)findViewById(R.id.Sixth_Back);
        sixthBack.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Sixth_Back:
                Intent intent = new Intent(getApplicationContext(), WayFifthActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }


}
