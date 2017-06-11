package com.anjinma.numberpang;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RankTimeAttackActivity extends AppCompatActivity implements View.OnClickListener {
    TextView infiniteGo = null;
    int count = 0;
    SQLiteDatabase db = null;
    TextView rank_Score1 = null;
    TextView rank_Score2 = null;
    TextView rank_Score3 = null;
    TextView timeattackGo = null;
    TextView timeattackBack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_time_attack);
        rank_Score1 = (TextView) findViewById(R.id.Rank_Score1);
        rank_Score2 = (TextView) findViewById(R.id.Rank_Score2);
        rank_Score3 = (TextView) findViewById(R.id.Rank_Score3);

        timeattackGo = (TextView) findViewById(R.id.Rank_TimeAttack_Go);
        timeattackGo.setOnClickListener(this);
        timeattackBack = (TextView) findViewById(R.id.Rank_TimeAttack_Back);
        timeattackBack.setOnClickListener(this);
        showRank("TimeAttack");
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.Rank_TimeAttack_Go:
                Intent intent = new Intent(getApplicationContext(), RankHellActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.Rank_TimeAttack_Back:
                Intent intent2 = new Intent(getApplicationContext(), RankChallengeActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    public void showRank(String tableName) {
        Cursor cursor;
        int count, score;
        String date;

        db = openOrCreateDatabase("scoreDB.db", MODE_PRIVATE, null);
        createTable(tableName);
        cursor = queryData(tableName);

        count = cursor.getCount();


        if(count >= 1) {
            cursor.moveToNext();
            rank_Score1.setText(cursor.getString(0));
        }
        if(count >= 2) {
            cursor.moveToNext();
            rank_Score2.setText(cursor.getString(0));
        }
        if(count >= 3) {
            cursor.moveToNext();
            rank_Score3.setText(cursor.getString(0));
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
        String sql = "select * from " + tableName + " order by score limit 3";
        Cursor cursor = db.rawQuery(sql, null);


        return cursor;
    }
}
