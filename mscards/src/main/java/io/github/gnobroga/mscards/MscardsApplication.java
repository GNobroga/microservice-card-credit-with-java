package io.github.gnobroga.mscards;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableRabbit
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
public class MscardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscardsApplication.class, args);
	}

}
