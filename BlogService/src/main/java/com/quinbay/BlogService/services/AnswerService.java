package com.quinbay.BlogService.services;


import com.quinbay.BlogService.api.AnswerInterface;
import com.quinbay.BlogService.model.AnsUpdateRequest;
import com.quinbay.BlogService.model.AnswerRequest;
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
    public Answers addAnswer(AnswerRequest answerRequest) {
        try {
            Answers answers=new Answers();
            answers.setAnscontent(answerRequest.getContent());
            answers.setAnsweredfor(answerRequest.getAnsweredFor());
            answers.setAnsweredby(answerRequest.getAnsweredBy());
            return answerRepository.save(answers);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Answers> getAnswerForBlog(int blogId){
        try {
            return answerRepository.findByAnsweredforAndIsdeleted(blogId, false);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ArrayList<Answers> getQuesId(int userId){
        try {
            return answerRepository.findByAnsweredbyAndIsdeleted(userId, false);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ResponseEntity<String> updateAnswer(AnsUpdateRequest ansUpdateRequest){
        try {
            Answers answer = answerRepository.findById(ansUpdateRequest.getAnsId());
            answer.setAnscontent(ansUpdateRequest.getContent());
            answer.setUpdateby(ansUpdateRequest.getUpdatedBy());
            answerRepository.save(answer);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateUpvotes(int ansId,Boolean checkVoteType){
        try {
            System.out.println("inside ans updateupvotes"+ansId);
            Answers answers = answerRepository.findById(ansId);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(
                    "http://localhost:8080/user/updateCreditpoints?UserId="+answers.getAnsweredby()+"&check="+checkVoteType+"", HttpMethod.PUT, entity, ResponseEntity.class).getBody();
            if(checkVoteType){answers.setUpvotes(answers.getUpvotes()+1);}
            else{answers.setUpvotes(answers.getUpvotes()-1);}
            answerRepository.save(answers);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<String> updateDownvotes(int ansId,Boolean checkVoteType){
        try {
            System.out.println("inside answer downcount");
            Answers answers = answerRepository.findById(ansId);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(
                    "http://localhost:8080/user/updateCreditpoints?UserId="+answers.getAnsweredby()+"&check="+!checkVoteType+"", HttpMethod.PUT, entity, ResponseEntity.class).getBody();
            if(checkVoteType){answers.setDownvotes(answers.getDownvotes()+1);}
            else{answers.setDownvotes(answers.getDownvotes()-1);}
            answerRepository.save(answers);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

//    @Override
//    public ResponseEntity<String> updateIsClosed(int ansId){
//        try {
//            Answers answers = answerRepository.findById(ansId);
//            answers.setIsclosed(true);
//            answerRepository.save(answers);
//            return new ResponseEntity("Successfully update",HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
//        }
//    }

    @Override
    public ResponseEntity<String> updateIsReported(int ansId,int reportedBy){
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
    public ResponseEntity deleteAnswer(int ansId){
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
