import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'rn-android-alarm-scheduler' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const RnAndroidAlarmScheduler = NativeModules.RnAndroidAlarmScheduler
  ? NativeModules.RnAndroidAlarmScheduler
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function playAlarm(duration: number): Promise<void> {
  return RnAndroidAlarmScheduler.playAlarm(duration);
}

export function scheduleAlarm(interval: number): Promise<void> {
  return RnAndroidAlarmScheduler.scheduleAlarm(interval);
}

export function cancelAlarm(): Promise<void> {
  return RnAndroidAlarmScheduler.cancelAlarms();
}
