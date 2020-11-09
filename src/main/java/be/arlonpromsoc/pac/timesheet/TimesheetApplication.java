package be.arlonpromsoc.pac.timesheet;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
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

import be.arlonpromsoc.pac.timesheet.controller.TimesheetController;

@SpringBootApplication
@EnableAutoConfiguration
public class TimesheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimesheetApplication.class, args);
	}
	@Autowired
	TimesheetController temp;
	
	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MqttPahoMessageHandler mqttOutbound() {
	    MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(MqttClient.generateClientId(), mqttClientFactory());
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

//      String hostUrl = "tcp://maqiatto.com:1883";
        String hostUrl = "tcp://debian:1883";
//      mqttConnectOptions.setPassword(password.toCharArray());
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
    public MqttClient mqttClient () {
    	try {
    	
			MqttClient mqttClient = new MqttClient( "tcp://debian:1883", "timesheet");
			mqttClient.connect();
			mqttClient.setCallback( new MqttCallback() {				
				@Override
				public void messageArrived(String topic, MqttMessage message) throws Exception {
					temp.storeMessage(message.getPayload().toString());
					
				}
				
				@Override
				public void deliveryComplete(IMqttDeliveryToken token) {
					System.out.println("Devlivery");
					
				}
				
				@Override
				public void connectionLost(Throwable cause) {
					System.out.println("passe connection lost");
					
				}
			});
			return mqttClient;
		} catch (MqttException e) {
			System.out.println("cannot create Mqtt client");
			return null;
		}
    }
	
}
