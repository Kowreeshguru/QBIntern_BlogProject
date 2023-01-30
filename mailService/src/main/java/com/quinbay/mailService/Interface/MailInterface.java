package com.quinbay.mailService.Interface;

import com.quinbay.mailService.models.MailDetails;

import java.util.ArrayList;

public interface MailInterface {


    String sendMail(ArrayList<String> mailList);
}


