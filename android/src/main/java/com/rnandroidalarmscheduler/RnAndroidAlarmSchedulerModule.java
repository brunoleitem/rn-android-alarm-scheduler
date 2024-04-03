package com.rnandroidalarmscheduler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = RnAndroidAlarmSchedulerModule.NAME)
public class RnAndroidAlarmSchedulerModule extends ReactContextBaseJavaModule {
  public static final String NAME = "RnAndroidAlarmScheduler";

  private final AlarmManager alarmManager;
  private PendingIntent alarmIntent;

  public RnAndroidAlarmSchedulerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    alarmManager = (AlarmManager) reactContext.getSystemService(Context.ALARM_SERVICE);
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void scheduleAlarm(int interval) {
    cancelAlarms();
    playAlarm(5000);
    long triggerAtMillis = System.currentTimeMillis();
    long repeatIntervalMillis = (long) interval * 60 * 1000; // Convert minutes to milliseconds
    Intent intent = new Intent(getReactApplicationContext(), AlarmReceiver.class);
    intent.putExtra("interval", interval);
    alarmIntent = PendingIntent.getBroadcast(getReactApplicationContext(), 0, intent, PendingIntent.FLAG_MUTABLE);
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, repeatIntervalMillis, alarmIntent);
  }

  @ReactMethod
  public void cancelAlarms() {
    if (alarmManager != null && alarmIntent != null) {
      alarmManager.cancel(alarmIntent);
      alarmIntent.cancel();
      alarmIntent = null;
    }
    Intent stopIntent = new Intent(getReactApplicationContext(), AlarmReceiverStop.class);
    getReactApplicationContext().sendBroadcast(stopIntent);
  }


  @ReactMethod
  public void playAlarm(int duration) {
    Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    Ringtone ringtone = RingtoneManager.getRingtone(getReactApplicationContext(), uri);

    final Handler handler = new Handler();
    final Runnable runnable = () -> {
      if(ringtone.isPlaying()) {
        ringtone.stop();
      }
    };
    ringtone.play();
    handler.postDelayed(runnable, duration);
  }
}
