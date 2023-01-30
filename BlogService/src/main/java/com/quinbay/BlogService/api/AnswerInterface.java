package com.quinbay.BlogService.api;


import com.quinbay.BlogService.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AnswerInterface {
    Answers add_answer(AnswerPojo answer);
    ArrayList<Answers> getAnswerForBlog(int blogId);
    ResponseEntity<String> update_answer(AnsUpdatePojo ansUpdatePojo);
    ResponseEntity delete_answer(int ansId);
}
