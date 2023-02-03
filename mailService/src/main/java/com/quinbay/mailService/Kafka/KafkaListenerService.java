package com.quinbay.mailService.Kafka;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.quinbay.mailService.Service.MailOp;
import com.quinbay.mailService.models.MailDetails;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

    public  MailDetails mailDetails=null;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MailOp mailOp;

    @KafkaListener(topics="send.MailListInfo",groupId="warehouse")
    public void getMailInfo(ConsumerRecord<?,String> consumerRecord){
        System.out.println("inside mail listener");

        try{
            mailDetails = objectMapper.readValue(consumerRecord.value(),new com.fasterxml.jackson.core.type.TypeReference<MailDetails>(){

            });
//            System.out.println(mailDetails.getMailidList());
            mailOp.sendMail(mailDetails.getMailidList());
        }catch (Exception e){

        }
    }
}
