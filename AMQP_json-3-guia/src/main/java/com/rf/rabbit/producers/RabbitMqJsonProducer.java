package com.rf.rabbit.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rf.rabbit.dto.User;

@Service
public class RabbitMqJsonProducer {
	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonProducer.class);

	private RabbitTemplate rabbitTemplate;

	public RabbitMqJsonProducer(RabbitTemplate rabbitTemplate) {
	        this.rabbitTemplate = rabbitTemplate;
	    }

	public void sendJsonMessage(User user) {
		LOGGER.info(String.format("Enviando mensaje JSON -> %s", user.toString()));
		rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
	}

}
