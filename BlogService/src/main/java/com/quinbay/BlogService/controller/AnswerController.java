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
    public Answers add_answer(@RequestBody AnswerPojo answerPojo)
    {
        return answerService.add_answer(answerPojo);
    }
    @GetMapping("/getAnswerForBlog")
    public ArrayList<Answers> get_userTags(@RequestParam int blogId){
        return answerService.getAnswerForBlog(blogId);
    }

    @PutMapping("/updateAnswer")
    public ResponseEntity update_answer(@RequestBody AnsUpdatePojo ansUpdatePojo)
    {
        return answerService.update_answer(ansUpdatePojo);
    }

    @PutMapping("/updateIsReported")
    public ResponseEntity update_isreported(@RequestParam int ansId,@RequestParam int reportedBy) { return answerService.update_isReported(ansId,reportedBy); }

//    @PutMapping("/updateUpvotes")
//    public ResponseEntity update_upvotes(@RequestParam int ansId,@RequestParam Boolean check) { return answerService.update_upvotes(ansId,check); }
//
//    @PutMapping("/updateDownvotes")
//    public ResponseEntity remove_upvotes(@RequestParam int ansId,@RequestParam Boolean check) { return answerService.update_downvotes(ansId,check); }

    @PutMapping("/updateIsClosed")
    public ResponseEntity update_isclosed(@RequestParam int ansId) { return answerService.update_isClosed(ansId); }

    @DeleteMapping("/deleteTag")
    public ResponseEntity delete_answer(@RequestParam int ansId)
    {
        return answerService.delete_answer(ansId);
    }

}
