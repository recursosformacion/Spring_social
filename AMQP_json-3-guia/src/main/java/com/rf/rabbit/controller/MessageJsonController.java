package com.rf.rabbit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rf.rabbit.dto.User;
import com.rf.rabbit.producers.RabbitMqJsonProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

	private RabbitMqJsonProducer jsonProducer;

    public MessageJsonController(RabbitMqJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping("/pub")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Enviando mensaje JSON a RabbitMQ ...");
    }
}
