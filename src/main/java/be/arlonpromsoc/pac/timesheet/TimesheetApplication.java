package be.arlonpromsoc.pac.timesheet;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
@EnableAutoConfiguration
public class TimesheetApplication {
	
	public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(TimesheetApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);
        		
    }
}
