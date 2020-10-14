package be.arlonpromsoc.pac.timesheet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import be.arlonpromsoc.pac.timesheet.controller.TimesheetController;
import be.arlonpromsoc.pac.timesheet.service.ActivityService;
import be.arlonpromsoc.pac.timesheet.service.ActivityServiceImpl;

@Configuration
public class MqttConfig {
	
	@Autowired
	TimesheetController temp;
	
	@Bean 
	public ActivityService service () {
		return new ActivityServiceImpl();
	}
	
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("tcp://debian:1883", "server",
                                                 "timesheet");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }
    @Bean 
    public ObjectMapper mapper() {
    	return new ObjectMapper();
    }
    

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
    	
        return new MessageHandler() {    	
        	
        	@Override
            public void handleMessage(Message<?> message) throws MessagingException {
				temp.storeMessage(message.getPayload().toString());
            }

        };
    }

}
