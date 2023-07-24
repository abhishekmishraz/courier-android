package com.gojek.alarm.pingsender

import android.content.Context
import com.gojek.mqtt.pingsender.AdaptiveGojekMqttPingSender
import com.gojek.mqtt.pingsender.GojekMqttPingSender

class AlarmPingSenderFactory private constructor() {
    companion object {
        fun createMqttPingSender(context: Context, pingSenderConfig: AlarmPingSenderConfig): GojekMqttPingSender {
            return AlarmPingSenderGojek(context.applicationContext, pingSenderConfig)
        }

        fun createAdaptiveMqttPingSender(context: Context, pingSenderConfig: AlarmPingSenderConfig): AdaptiveGojekMqttPingSender {
            return AdaptiveAlarmPingSenderGojek(context.applicationContext, pingSenderConfig)
        }
    }
}
