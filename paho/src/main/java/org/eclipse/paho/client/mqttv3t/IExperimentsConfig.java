package org.eclipse.paho.client.mqttv3t;

public interface IExperimentsConfig {
    int inactivityTimeoutSecs();

    int connectPacketTimeoutSecs();

    Boolean useNewSSLFlow();
}
