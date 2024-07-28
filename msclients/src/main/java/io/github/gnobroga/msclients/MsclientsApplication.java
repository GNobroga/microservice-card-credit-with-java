package io.github.gnobroga.msclients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableEurekaClient
@SpringBootApplication
public class MsclientsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MsclientsApplication.class, args);
	}

}
