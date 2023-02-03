package com.quinbay.BlogService.controller;
import com.quinbay.BlogService.model.*;
import com.quinbay.BlogService.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody CommentRequest commentRequest)
    {
        return commentService.addComment(commentRequest);
    }

    @GetMapping("/getComment")
    public ArrayList<Comment> getUserTags(@RequestParam int commentId,@RequestParam ParentType type){
        return commentService.getComment(commentId,type);
    }

    @PutMapping("/updateComments")
    public ResponseEntity updateComment(@RequestBody ComUpdateRequest comUpdateRequest)
    {
        return commentService.updateComment(comUpdateRequest);
    }

    @DeleteMapping("/deleteComment")
    public ResponseEntity deleteComment(@RequestParam int commentId)
    {
        return commentService.deleteComment(commentId);
    }

    @PutMapping("/updateIsReported")
    public ResponseEntity updateIsreported(@RequestParam int commentId,@RequestParam int reportedBy) { return commentService.updateIsReported(commentId,reportedBy); }

}
