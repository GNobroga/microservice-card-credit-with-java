package io.github.gnobroga.mscreditavaluator.infrastructure.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String CARD_ISSUANCE_QUEUE_NAME = "card-issuance-queue";
    public static final String CARD_ISSUANCE_EXCHANGE_NAME = "card-issuance-exchange";
    public static final String CARD_ISSUANCE_ROUTING_NAME = "card-issuance-routing-key";
    
    @Bean
    Queue queueCardIssuance() {
        return new Queue(CARD_ISSUANCE_QUEUE_NAME, true);
    }

    @Bean
    DirectExchange directExchangeCardIssuance() {
        return new DirectExchange(CARD_ISSUANCE_EXCHANGE_NAME);
    }

    @Bean
    Binding queueBindingCardIssuance() {
        return BindingBuilder.bind(queueCardIssuance())
            .to(directExchangeCardIssuance())
            .with(CARD_ISSUANCE_ROUTING_NAME);
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

}
