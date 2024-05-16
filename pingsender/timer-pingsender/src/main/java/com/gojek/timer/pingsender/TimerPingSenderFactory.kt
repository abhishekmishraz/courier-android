package com.gojek.timer.pingsender

import com.gojek.mqtt.pingsender.GojekMqttPingSender

class TimerPingSenderFactory private constructor() {
    companion object {
        fun create(timerPingSenderConfig: TimerPingSenderConfig): GojekMqttPingSender {
            return TimerPingSenderGojek(timerPingSenderConfig)
        }
    }
}
