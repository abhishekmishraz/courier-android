package com.gojek.mqtt.model

import com.gojek.mqtt.pingsender.AdaptiveGojekMqttPingSender

data class AdaptiveKeepAliveConfig(
    val lowerBoundMinutes: Int,
    val upperBoundMinutes: Int,
    val stepMinutes: Int,
    val optimalKeepAliveResetLimit: Int,
    val pingSender: AdaptiveGojekMqttPingSender
)
