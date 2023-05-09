package com.rf.rabbit.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rf.rabbit.dto.User;

@Service
public class RabbitMqJsonConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);

	@RabbitListener(queues = { "${rabbitmq.queue.json.name}" })
	public void consumeJsonMessage(User user) {
		LOGGER.info(String.format("Recibiendo mensaje JSON -> %s", user.toString()));
	}

}
