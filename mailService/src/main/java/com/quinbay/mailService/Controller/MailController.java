package com.quinbay.mailService.Controller;


// Importing required classes

import com.quinbay.mailService.Interface.MailInterface;
import com.quinbay.mailService.models.MailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MailController {

//    @Autowired private MailInterface emailService;
//
//
//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(
//            @RequestBody MailDetails details)
//    {
//        String status
//                = emailService.sendMailWithAttachment(details);
//
//        return status;
//    }
}
