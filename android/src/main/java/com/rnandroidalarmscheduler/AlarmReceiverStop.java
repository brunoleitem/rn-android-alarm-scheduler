package com.rnandroidalarmscheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiverStop extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (AlarmReceiver.ringtone != null) {
            AlarmReceiver.ringtone.stop();
        }
        if (AlarmReceiver.countDownTimer != null) {
            AlarmReceiver.countDownTimer.cancel();
        }
        AlarmReceiver.countDownTimer = null;
        AlarmReceiver.ringtone = null;
    }
}
