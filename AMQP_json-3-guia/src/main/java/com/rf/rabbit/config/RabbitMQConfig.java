package com.rf.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.queue.name}")
	private String queue;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	@Value("${rabbitmq.queue.json.name}")
	private String jsonQueue;

	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;

	@Value("${rabbitmq.queue.comun.name}")
	private String comunName;

	@Value("${rabbitmq.routing.comun.key}")
	private String routingComunKey;

	// -------------exchange----------------------------
	// spring bean for rabbitmq exchange
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(exchange);
	}

	// -----------------queue----------------------------
	// spring bean for rabbitmq queue
	@Bean
	public Queue queue() {
		return new Queue(queue,false,false,true);
	}

	// spring bean for queue (store json messages)
	@Bean
	public Queue jsonQueue() {
		return new Queue(jsonQueue,false);
	}

	// spring bean for queue (store comun messages)
	@Bean
	public Queue comunQueue() {
		return new Queue(comunName,false);
	}

	//-------------------binding--------------------------------
	// binding between queue and exchange using routing key
	@Bean
	public Binding binding() {
		return BindingBuilder
				.bind(queue())
				.to(exchange())
				.with(routingKey);
	}

	// binding between json queue and exchange using routing key
	@Bean
	public Binding jsonBinding() {
		return BindingBuilder
				.bind(jsonQueue())
				.to(exchange())
				.with(routingJsonKey);
	}

	// binding between comun queue and exchange using routing key
	@Bean
	public Binding comunBinding() {
		return BindingBuilder
				.bind(comunQueue())
				.to(exchange())
				.with(routingComunKey);
	}

	@Bean
	public MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

	// ConnectionFactory
	// RabbitTemplate
	// RabbitAdmin
}
