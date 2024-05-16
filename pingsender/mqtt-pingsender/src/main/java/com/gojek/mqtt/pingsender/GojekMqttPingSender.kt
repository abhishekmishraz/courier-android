package com.gojek.mqtt.pingsender

import androidx.annotation.RestrictTo
import org.eclipse.paho.client.mqttv3t.ILogger
import org.eclipse.paho.client.mqttv3t.MqttPingSender as PahoPingSender
import org.eclipse.paho.client.mqttv3t.internal.ClientComms

interface GojekMqttPingSender {
    fun init(comms: ClientComms, logger: ILogger)
    fun start()
    fun stop()
    fun schedule(delayInMilliseconds: Long)

    @RestrictTo(RestrictTo.Scope.LIBRARY)
    fun setPingEventHandler(pingSenderEvents: IPingSenderEvents)
}

@RestrictTo(RestrictTo.Scope.LIBRARY)
fun GojekMqttPingSender.toPahoPingSender(): PahoPingSender {
    val mqttPingSender = this
    return object : PahoPingSender {
        override fun init(comms: ClientComms, logger: ILogger) {
            mqttPingSender.init(comms, logger)
        }

        override fun start() {
            mqttPingSender.start()
        }

        override fun stop() {
            mqttPingSender.stop()
        }

        override fun schedule(delayInMilliseconds: Long) {
            mqttPingSender.schedule(delayInMilliseconds)
        }
    }
}
