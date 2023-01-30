package com.quinbay.user.service.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.user.service.model.TagTransfer;
import com.quinbay.user.service.model.UserTag;
import com.quinbay.user.service.service.UsersTagService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service
public class KafkaListeningService {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UsersTagService usersTagService;


    @KafkaListener(topics = "send.TagListInfo",groupId = "warehouse")
    public void getTagListInfo (ConsumerRecord<?,String> consumerRecord){
        TagTransfer tagTransfer = null ;

        try{
            tagTransfer = objectMapper.readValue(consumerRecord.value(),
                    new TypeReference<TagTransfer>(){
                    });
        usersTagService.mail_service(tagTransfer.getTagList());
        }catch (Exception e){

        }
    }
}

