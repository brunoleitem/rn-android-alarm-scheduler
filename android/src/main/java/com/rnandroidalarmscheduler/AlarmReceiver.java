package com.rnandroidalarmscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;

public class AlarmReceiver extends BroadcastReceiver {
    public static Ringtone ringtone;

    public static CountDownTimer countDownTimer;

    @Override
    public void onReceive(Context context, Intent intent) {
        int interval = intent.getIntExtra("interval", 0);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        ringtone = RingtoneManager.getRingtone(context, uri);
        ringtone.play();

        if(interval == 0) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ringtone.stop();
                }
            }, 5000);
        } else if(interval > 0) {
            ringtone.play();
            long count = interval * 1000L;
            countDownTimer = new CountDownTimer(count, 5000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    if(ringtone != null) {
                        ringtone.stop();
                    }
                }
            }.start();
        }
    }
}
