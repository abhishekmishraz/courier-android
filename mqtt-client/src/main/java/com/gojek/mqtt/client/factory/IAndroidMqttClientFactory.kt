package com.gojek.mqtt.client.factory

import android.content.Context
import com.gojek.courier.logging.NoOpLogger
import com.gojek.keepalive.KeepAliveFailureHandler
import com.gojek.mqtt.client.config.v3.MqttV3Configuration
import com.gojek.mqtt.client.internal.KeepAliveProvider
import com.gojek.mqtt.client.v3.IAndroidMqttClient
import com.gojek.mqtt.client.v3.impl.AndroidMqttClient
import com.gojek.mqtt.event.EventHandler
import com.gojek.mqtt.event.NoOpEventHandler
import com.gojek.mqtt.pingsender.IPingSenderEvents
import com.gojek.mqtt.pingsender.GojekMqttPingSender
import com.gojek.networktracker.NetworkStateTracker

internal interface IAndroidMqttClientFactory {
    fun createAndroidMqttClient(
        context: Context,
        mqttConfiguration: MqttV3Configuration,
        networkStateTracker: NetworkStateTracker,
        keepAliveProvider: KeepAliveProvider,
        keepAliveFailureHandler: KeepAliveFailureHandler,
        eventHandler: EventHandler,
        pingEventHandler: IPingSenderEvents,
        shouldRetryOnException : Boolean = false,
    ): IAndroidMqttClient
    fun createAdaptiveAndroidMqttClient(
        pingSender: GojekMqttPingSender,
        context: Context,
        mqttConfiguration: MqttV3Configuration,
        networkStateTracker: NetworkStateTracker,
        keepAliveProvider: KeepAliveProvider,
        keepAliveFailureHandler: KeepAliveFailureHandler,
        pingEventHandler: IPingSenderEvents,
        shouldRetryOnException : Boolean = false
    ): IAndroidMqttClient
}

internal class AndroidMqttClientFactory : IAndroidMqttClientFactory {
    override fun createAndroidMqttClient(
        context: Context,
        mqttConfiguration: MqttV3Configuration,
        networkStateTracker: NetworkStateTracker,
        keepAliveProvider: KeepAliveProvider,
        keepAliveFailureHandler: KeepAliveFailureHandler,
        eventHandler: EventHandler,
        pingEventHandler: IPingSenderEvents,
        shouldRetryOnException : Boolean
    ): IAndroidMqttClient {
        val pingSender = mqttConfiguration.pingSender
        pingSender.setPingEventHandler(pingEventHandler)
        return AndroidMqttClient(
            context = context,
            mqttConfiguration = mqttConfiguration,
            networkStateTracker = networkStateTracker,
            gojekMqttPingSender = pingSender,
            keepAliveProvider = keepAliveProvider,
            keepAliveFailureHandler = keepAliveFailureHandler,
            eventHandler = eventHandler,
            shouldReconnectOnException = shouldRetryOnException
        )
    }

    override fun createAdaptiveAndroidMqttClient(
        pingSender: GojekMqttPingSender,
        context: Context,
        mqttConfiguration: MqttV3Configuration,
        networkStateTracker: NetworkStateTracker,
        keepAliveProvider: KeepAliveProvider,
        keepAliveFailureHandler: KeepAliveFailureHandler,
        pingEventHandler: IPingSenderEvents,
        shouldRetryOnException : Boolean
    ): IAndroidMqttClient {
        pingSender.setPingEventHandler(pingEventHandler)
        return AndroidMqttClient(
            context = context,
            mqttConfiguration = mqttConfiguration.copy(
                logger = NoOpLogger()
            ),
            networkStateTracker = networkStateTracker,
            gojekMqttPingSender = pingSender,
            isAdaptiveKAConnection = true,
            keepAliveProvider = keepAliveProvider,
            keepAliveFailureHandler = keepAliveFailureHandler,
            eventHandler = NoOpEventHandler(),
            shouldReconnectOnException = shouldRetryOnException
        )
    }
}

internal fun getAndroidMqttClientFactory(): IAndroidMqttClientFactory {
    return AndroidMqttClientFactory()
}
