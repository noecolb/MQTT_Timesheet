package be.arlonpromsoc.pac.timesheet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.arlonpromsoc.pac.timesheet.service.ActivityService;
import be.arlonpromsoc.pac.timesheet.service.ActivityServiceImpl;

@Configuration
public class MqttConfig {
	
		
	@Bean 
	public ActivityService service () {
		return new ActivityServiceImpl();
	}
	
	
	
//    @Bean
//    public MessageChannel mqttInputChannel() {
//        return new DirectChannel();
//    }
//    
//    @Bean
//    public MqttPahoClientFactory mqttClientFactory() {
//        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setServerURIs(new String[] { "tcp://debian:1883" });
//        factory.setConnectionOptions(options);
//        return factory;
//    }
//
//    @Bean
//    public MessageProducer inbound() {
//        MqttPahoMessageDrivenChannelAdapter adapter =
//                new MqttPahoMessageDrivenChannelAdapter("tcp://debian:1883", "server",
//                                                 "timesheet");
//        adapter.setCompletionTimeout(5000);
//        adapter.setConverter(new DefaultPahoMessageConverter());
//        adapter.setQos(1);
//        
//        adapter.setOutputChannel(mqttInputChannel());
//        return adapter;
//    }
    @Bean 
    public ObjectMapper mapper() {
    	return new ObjectMapper();
    }
//    
//
//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")
//    public MessageHandler handler() {
//    	
//        return new MessageHandler() {    	
//        	
//        	@Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//				temp.storeMessage(message.getPayload().toString());
//            }
//
//        };
//    }
   
}
