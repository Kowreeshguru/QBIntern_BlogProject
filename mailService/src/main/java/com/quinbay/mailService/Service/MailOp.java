package com.quinbay.mailService.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.quinbay.mailService.Interface.MailInterface;
//import com.quinbay.mailService.Kafka.KafkaListenerService;
//import com.quinbay.mailService.Kafka.KafkaListenerService;
import com.quinbay.mailService.models.MailDetails;

import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class MailOp implements MailInterface {

    @Autowired private JavaMailSender javaMailSender;
    @Autowired
//    KafkaListenerService kafkaListenerService;

    @Value("${spring.mail.username}") private String sender;
    public String sendMail(ArrayList<String> mailList)
    {
//        System.out.println("inside mailservice");
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        String mailIds[] = mailList.toArray(new String[mailList.size()]);
        try {
            ArrayList<String> mailIdList=new ArrayList<>();

            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setText("Question for you");
            mimeMessageHelper.setSubject("Exploer your intrested Tag");
            mimeMessageHelper.setBcc(mailIds);
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }
        catch (Exception e) {
            return "Error while sending mail!!!";
        }

    }

}
