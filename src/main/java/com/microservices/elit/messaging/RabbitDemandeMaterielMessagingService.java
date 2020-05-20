package com.microservices.elit.messaging;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.elit.model.DemandeCRUD;

@Service
public class RabbitDemandeMaterielMessagingService
        {
  
  private RabbitTemplate rabbit;
  
  @Autowired
  public RabbitDemandeMaterielMessagingService(RabbitTemplate rabbit) {
    this.rabbit = rabbit;
  }
  
  public void sendDemande(DemandeCRUD test) {
	try {  
	new Queue("DemandeMateriel.queue");}
	catch(Exception e) {}
    rabbit.convertAndSend("DemandeMateriel.queue", test);
  }
  
}