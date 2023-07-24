package com.gojek.mqtt.send.listener

import com.gojek.mqtt.client.model.MqttSendPacket
import org.eclipse.paho.client.mqttv3t.IMqttToken

internal interface IMessageSendListener {
    fun onSuccess(packet: MqttSendPacket, iMqttToken: IMqttToken)
    fun onFailure(packet: MqttSendPacket, exception: Throwable)
    fun notifyWrittenOnSocket(packet: MqttSendPacket)
}
