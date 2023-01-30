package com.quinbay.BlogService.kafka;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.BlogService.model.TagTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class KafkaPublishingService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void BlogTagInfo (TagTransfer tagList){
        try{
            System.out.println("Kafka Publisher called");
            System.out.println(tagList);
            kafkaTemplate.send("send.TagListInfo",this.objectMapper.writeValueAsString(tagList));
        }
        catch (Exception e){
            System.out.println("Error Occurred"+e);
        }
    }
}
