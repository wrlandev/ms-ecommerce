package com.msecommerce.clientems.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "pedido_criado", groupId = "cliente-group")
    public void consumirEventoPedidoCriado(String message) {
        logger.info("=================================================");
        logger.info("EVENTO 'PEDIDO CRIADO' RECEBIDO NO CLIENTE-MS");
        logger.info("Payload: {}", message);
        logger.info("=================================================");
    }
}