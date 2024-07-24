package io.github.gnobroga.mscreditavaluator.infrastructure.event;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMQEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private final RabbitAdmin rabbitAdmin;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        rabbitAdmin.initialize();
    }
    
}
