package com.gojek.mqtt.connection

import org.eclipse.paho.client.mqttv3t.IMqttActionListener
import org.eclipse.paho.client.mqttv3t.IMqttToken

internal class NoOpConnectListener : IMqttActionListener {
    override fun onSuccess(asyncActionToken: IMqttToken?) {
    }

    override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
    }
}
