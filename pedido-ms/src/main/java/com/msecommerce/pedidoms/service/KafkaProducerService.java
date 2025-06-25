package com.msecommerce.pedidoms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private static final String TOPIC = "pedido_criado";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void enviarEventoPedidoCriado(String mensagem) {
        logger.info("Enviando evento para o tópico {}: {}", TOPIC, mensagem);

        this.kafkaTemplate.send(TOPIC, mensagem);
    }
}