package org.eclipse.paho.client.mqttv3t;

public interface IMqttActionListenerNew extends IMqttActionListener
{
	public void notifyWrittenOnSocket(IMqttToken asyncActionToken);
}
