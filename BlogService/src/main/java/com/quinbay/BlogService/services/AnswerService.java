package com.quinbay.BlogService.services;


import com.quinbay.BlogService.api.AnswerInterface;
import com.quinbay.BlogService.model.AnsUpdatePojo;
import com.quinbay.BlogService.model.AnswerPojo;
import com.quinbay.BlogService.model.Answers;
import com.quinbay.BlogService.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class AnswerService implements AnswerInterface {

    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Answers add_answer(AnswerPojo answerPojo) {
        try {
            Answers answers=new Answers();
            answers.setAnscontent(answerPojo.getContent());
            answers.setAnsweredfor(answerPojo.getAnsweredFor());
            answers.setAnsweredby(answerPojo.getAnsweredBy());
            return answerRepository.save(answers);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Answers> getAnswerForBlog(int blogId){return answerRepository.findByAnsweredforAndIsdeleted(blogId,false);}

    @Override
    public ResponseEntity<String> update_answer(AnsUpdatePojo ansUpdatePojo){
        try {
            Answers answer = answerRepository.findById(ansUpdatePojo.getAnsId());
            answer.setAnscontent(ansUpdatePojo.getContent());
            answer.setUpdateby(ansUpdatePojo.getUpdatedBy());
            answerRepository.save(answer);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_upvotes(int ansId,Boolean check){
        try {
            Answers answers = answerRepository.findById(ansId);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(
                    "http://localhost:8080/user/updateCreditpoints?UserId="+answers.getAnsweredby()+"&check="+check+"", HttpMethod.PUT, entity, ResponseEntity.class).getBody();
            if(check){answers.setUpvotes(answers.getUpvotes()+1);}
            else{answers.setUpvotes(answers.getUpvotes()-1);}
            answerRepository.save(answers);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_downvotes(int ansId,Boolean check){
        try {
            Answers answers = answerRepository.findById(ansId);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(
                    "http://localhost:8080/user/updateCreditpoints?UserId="+answers.getAnsweredby()+"&check="+!check+"", HttpMethod.PUT, entity, ResponseEntity.class).getBody();
            if(check){answers.setDownvotes(answers.getDownvotes()+1);}
            else{answers.setDownvotes(answers.getDownvotes()-1);}
            answerRepository.save(answers);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_isClosed(int ansId){
        try {
            Answers answers = answerRepository.findById(ansId);
            answers.setIsclosed(true);
            answerRepository.save(answers);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_isReported(int ansId,int reportedBy){
        try {
            Answers answers = answerRepository.findById(ansId);
            answers.setIsreported(true);
            answers.setReportedby(reportedBy);
            answerRepository.save(answers);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity delete_answer(int ansId){
        try {
            Answers answers = answerRepository.findById(ansId);
            answers.setIsdeleted(true);
            answerRepository.save(answers);
            return new ResponseEntity("Successfully deleted",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity("Check your username and password",HttpStatus.BAD_REQUEST);
        }
    }
}
