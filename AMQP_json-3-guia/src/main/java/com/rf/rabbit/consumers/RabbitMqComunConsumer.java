package com.rf.rabbit.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rf.rabbit.dto.User;

@Service
public class RabbitMqComunConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqComunConsumer.class);

	@RabbitListener(queues = { "${rabbitmq.queue.comun.name}" })
	public void consumeJsonMessage(Object user) {
		LOGGER.info(String.format("Recibiendo mensaje comun -> %s", user.toString()));
	}

}
