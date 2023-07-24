package com.gojek.mqtt.pingsender

import androidx.annotation.RestrictTo

interface AdaptiveGojekMqttPingSender : GojekMqttPingSender {
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    fun setKeepAliveCalculator(keepAliveCalculator: KeepAliveCalculator)
}
