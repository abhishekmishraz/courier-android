package com.gojek.mqtt.send.listener

import com.gojek.mqtt.client.model.MqttSendPacket
import org.eclipse.paho.client.mqttv3t.IMqttToken

internal class NoOpMessageSendListener :
    IMessageSendListener {
    override fun onSuccess(packet: MqttSendPacket, iMqttToken: IMqttToken) {
    }

    override fun onFailure(packet: MqttSendPacket, exception: Throwable) {
    }

    override fun notifyWrittenOnSocket(packet: MqttSendPacket) {
    }
}
