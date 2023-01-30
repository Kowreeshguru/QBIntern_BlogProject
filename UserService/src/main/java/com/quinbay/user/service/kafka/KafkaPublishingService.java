package com.quinbay.user.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.user.service.model.MailId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaPublishingService {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate kafkaTemplate;

    public void MailTagInfo (MailId mailId){
        try{
            System.out.println("Kafka Publisher called");
            for(String str:mailId.getMailidList()){
                System.out.println(str);
            }
            kafkaTemplate.send("send.MailListInfo",this.objectMapper.writeValueAsString(mailId));
        }
        catch (Exception e){
            System.out.println("Error Occurred"+e);
        }
    }
}
