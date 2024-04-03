/* eslint-disable react-native/no-inline-styles */
import * as React from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import {
  playAlarm,
  cancelAlarm,
  scheduleAlarm,
} from 'rn-android-alarm-scheduler';

export default function App() {
  const playAlarmTest = () => {
    playAlarm(6000);
  };

  const scheduleAlarmTest = () => {
    // Time in minutes
    scheduleAlarm(1);
  };

  const cancelAlarms = () => {
    cancelAlarm();
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity
        onPress={playAlarmTest}
        style={{
          marginBottom: 16,
          borderColor: 'black',
          borderWidth: 1,
          padding: 4,
        }}
      >
        <Text>Play 6 seconds</Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={scheduleAlarmTest}
        style={{
          marginBottom: 16,
          borderColor: 'black',
          borderWidth: 1,
          padding: 4,
        }}
      >
        <Text>Schedule 1 minute</Text>
      </TouchableOpacity>
      <TouchableOpacity
        onPress={cancelAlarms}
        style={{
          marginBottom: 16,
          borderColor: 'black',
          borderWidth: 1,
          padding: 4,
        }}
      >
        <Text>Cancel</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
