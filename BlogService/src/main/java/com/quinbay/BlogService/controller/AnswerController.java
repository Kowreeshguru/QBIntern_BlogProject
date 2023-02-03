package com.quinbay.BlogService.controller;


import com.quinbay.BlogService.model.*;
import com.quinbay.BlogService.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/answer")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @PostMapping("/addAnswer")
    public Answers addAnswer(@RequestBody AnswerRequest answerRequest)
    {
        return answerService.addAnswer(answerRequest);
    }
    @GetMapping("/getAnswerForBlog")
    public ArrayList<Answers> getUserTags(@RequestParam int blogId){
        return answerService.getAnswerForBlog(blogId);
    }

    @PutMapping("/updateAnswer")
    public ResponseEntity updateAnswer(@RequestBody AnsUpdateRequest ansUpdateRequest)
    {
        return answerService.updateAnswer(ansUpdateRequest);
    }

    @PutMapping("/updateIsReported")
    public ResponseEntity updateIsreported(@RequestParam int ansId,@RequestParam int reportedBy) { return answerService.updateIsReported(ansId,reportedBy); }

//    @PutMapping("/updateUpvotes")
//    public ResponseEntity updateUpvotes(@RequestParam int ansId,@RequestParam Boolean check) { return answerService.update_upvotes(ansId,check); }
//
//    @PutMapping("/updateDownvotes")
//    public ResponseEntity removeUpvotes(@RequestParam int ansId,@RequestParam Boolean check) { return answerService.update_downvotes(ansId,check); }

//    @PutMapping("/updateIsClosed")
//    public ResponseEntity updateIsclosed(@RequestParam int ansId) { return answerService.updateIsClosed(ansId); }

    @DeleteMapping("/deleteTag")
    public ResponseEntity deleteAnswer(@RequestParam int ansId)
    {
        return answerService.deleteAnswer(ansId);
    }

}
