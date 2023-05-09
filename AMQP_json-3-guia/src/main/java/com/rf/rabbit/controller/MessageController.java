package com.rf.rabbit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rf.rabbit.producers.RabbitMqProducerString;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

	private RabbitMqProducerString producer;

	public MessageController(RabbitMqProducerString producer) {
		this.producer = producer;
	}

	
	@GetMapping("/pub")
	public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
		producer.sendMessage(message);
		return ResponseEntity.ok("Enviando mensaje a RabbitMQ ...");
	}
}
