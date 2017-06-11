package com.anjinma.numberpang;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TierTimeAttackActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteDatabase db = null;
    TextView tierTimeAttack= null;
    TextView tierTimeAttackGo = null;
    TextView tierTimeAttackBack = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tier_time_attack);
        tierTimeAttack = (TextView)findViewById(R.id.Tier_TimeAttack);
        tierTimeAttackBack = (TextView) findViewById(R.id.Tier_TimeAttack_Go);
        tierTimeAttackBack.setOnClickListener(this);
        tierTimeAttackGo = (TextView) findViewById(R.id.Tier_TimeAttack_Back);
        tierTimeAttackGo.setOnClickListener(this);

        setTeer("TimeAttack");

    }

    public void setTeer(String tableName) {
        Cursor cursor;
        double score;

        db = openOrCreateDatabase("scoreDB.db", MODE_PRIVATE, null);
        createTable(tableName);
        cursor = queryData(tableName);

        if(cursor.getCount() == 1) {
            cursor.moveToNext();
            score = Double.parseDouble(cursor.getString(0));

            if(score > 80) {
                tierTimeAttack.setText("스톤");
            } else if(score <= 80 && score > 70) {
                tierTimeAttack.setText("브론즈");
            } else if(score <= 70 && score > 60) {
                tierTimeAttack.setText("실버");
            } else if(score <= 60 && score > 50) {
                tierTimeAttack.setText("골드");
            } else if(score <= 50 && score > 40) {
                tierTimeAttack.setText("플래티넘");
            } else if(score <= 40 && score > 35) {
                tierTimeAttack.setText("다이아");
            } else if(score <= 35 && score > 30) {
                tierTimeAttack.setText("마스터");
            } else if(score <= 30) {
                tierTimeAttack.setText("그랜드마스터");
            }

        }
    }

    private void createDatabase() {
        String name = "scoreDB.db";
        db = SQLiteDatabase.openOrCreateDatabase(name,  null);
    }

    private void createTable(String tableName) {

        String sql = "create table " + tableName + " (score int, date text)";

        try {
            db.execSQL(sql);//slq문 실행
        } catch (Exception e) {
            //Toast.makeText(getApplicationContext(), "Create Table 오류", Toast.LENGTH_LONG).show();
        }
    }

    public Cursor queryData(String tableName) {
        String sql = "select * from " + tableName + " order by score limit 1";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Tier_TimeAttack_Back:
                Intent intent = new Intent(getApplicationContext(), TierChallengeActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Tier_TimeAttack_Go:
                Intent intent2 = new Intent(getApplicationContext(), TierHellActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }
}
