package com.socialmediamicroservice.notificationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.socialmediamicroservice.notificationservice.pojo.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListenerService {
    @Autowired
    EmailServiceImpl emailService;

    @KafkaListener(topics = "post-topic", groupId = "group-id")
    public void listen(String message){

        log.info("Received Message in group - group-id: " + message);
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try{Post post = objectMapper.readValue(message,Post.class);
        emailService.sendMessage(post);}
        catch (JsonProcessingException jsonProcessingException){
            log.error(jsonProcessingException.getMessage());
        }
    }
}
