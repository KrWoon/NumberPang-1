package com.anjinma.numberpang;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class OptionActivity extends Activity {
    public static boolean effect_SoundCheck = true;
    public static boolean background_SoundCheck = false;
    public SharedPreferences backgroundPreferences;
    public SharedPreferences effectPreferences;
    Switch effect_SW = null;
    Switch background_SW = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        effect_SW = (Switch)findViewById(R.id.EffectSound);
        background_SW = (Switch)findViewById(R.id.BackgroundSound);

        backgroundPreferences = getSharedPreferences("background", MODE_PRIVATE);
        effectPreferences = getSharedPreferences("effect", MODE_PRIVATE);

        boolean isBackgroundOn = backgroundPreferences.getBoolean("background", true);  //default is true
        boolean isEffectOn = effectPreferences.getBoolean("effect", true);  //default is true

        background_SW.setChecked(isBackgroundOn);
        effect_SW.setChecked(isEffectOn);

        background_SW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor backgroundEditor = backgroundPreferences.edit();
                backgroundEditor.putBoolean("background", background_SW.isChecked());
                backgroundEditor.apply();
            }
        });

        effect_SW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor effectEditor = effectPreferences.edit();
                effectEditor.putBoolean("effect", effect_SW.isChecked());
                effectEditor.apply();
            }
        });

    }
}