package com.quinbay.BlogService.api;


import com.quinbay.BlogService.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AnswerInterface {
    Answers addAnswer(AnswerRequest answer);
    ArrayList<Answers> getAnswerForBlog(int blogId);
//    ResponseEntity<String> updateAcceptedAnswer(int blogId,int ansId);
    ResponseEntity<String> updateAnswer(AnsUpdateRequest ansUpdateRequest);
    ArrayList<Answers> getQuesId(int userId);
    ResponseEntity<String> updateIsReported(int ansId,int reportedBy);
    ResponseEntity<String> updateDownvotes(int ansId,Boolean checkVoteType);
    ResponseEntity<String> updateUpvotes(int ansId,Boolean checkVoteType);
//    ResponseEntity<String> updateIsClosed(int ansId);
    ResponseEntity deleteAnswer(int ansId);
}
