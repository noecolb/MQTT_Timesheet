package be.arlonpromsoc.pac.timesheet;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;

import be.arlonpromsoc.pac.timesheet.controller.StorageController;

@SpringBootApplication
@EnableAutoConfiguration
public class TimesheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetApplication.class, args);
	}

	@Autowired
	StorageController temp;

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MqttPahoMessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MqttClient.generateClientId(),
				mqttClientFactory());
		return messageHandler;
	}

	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

	@Bean
	public MqttConnectOptions getReceiverMqttConnectOptions() {
		MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		mqttConnectOptions.setCleanSession(true);
		mqttConnectOptions.setConnectionTimeout(30);
		mqttConnectOptions.setKeepAliveInterval(60);
		mqttConnectOptions.setAutomaticReconnect(true);

		String hostUrl = "tcp://debian:1883";
		mqttConnectOptions.setServerURIs(new String[] { hostUrl });
		return mqttConnectOptions;
	}

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setConnectionOptions(getReceiverMqttConnectOptions());
		return factory;
	}

	@Bean
	public MessageProducer inbound() {
		String clientId2 = "uuid-" + UUID.randomUUID().toString();
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId2,
//              mqttClientFactory(), "myemail/test");
				mqttClientFactory(), "test", "test/paho");
		adapter.setCompletionTimeout(20000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(2);
		adapter.setOutputChannel(mqttOutboundChannel());
		return adapter;
	}

	@Bean
	public MqttClient mqttClient() throws MqttException {

		MqttClient client = (MqttClient) mqttClientFactory().getClientInstance("tcp://debian:1883", MqttClient.generateClientId());
		client.connect();
		IMqttMessageListener listener = new IMqttMessageListener() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				temp.storeMessage(message.toString());
				
			}
		};
		client.subscribe("timesheet", listener);
		client.subscribe("activity", new IMqttMessageListener() {
			
			@Override
			public void messageArrived(String topic, MqttMessage message) throws Exception {
				temp.storeActivity(message.toString());
				
			}
		});
		return client;
	}

}
