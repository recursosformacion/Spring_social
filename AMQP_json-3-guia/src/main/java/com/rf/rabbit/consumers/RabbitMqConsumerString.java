package com.rf.rabbit.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumerString {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumerString.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(String message){
        LOGGER.info(String.format("Recibiendo mensaje String -> %s", message));
    }
}
