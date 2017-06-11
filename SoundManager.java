package com.anjinma.numberpang;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

/**
 * Created by 박지운 on 2017-06-05.
 */

public class SoundManager extends Activity{
    public static SoundPool soundpool;
    public static int soundId = 0;

    public void SoundManager() {
        soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId = soundpool.load(this, R.raw.beep, 1);
    }

    public static void Play() {
        soundpool.play(soundId, 1.0F, 1.0F,  1,  0,  1.0F); // soundId, leftVolum, rightVolum, priority, loop, rate
    }

    public static void Stop() {
        soundpool.stop(0);
    }
}
