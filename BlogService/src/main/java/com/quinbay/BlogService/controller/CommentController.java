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
    public Comment add_comment(@RequestBody CommentPojo commentPojo)
    {
        return commentService.add_comment(commentPojo);
    }

    @GetMapping("/getComment")
    public ArrayList<Comment> get_userTags(@RequestParam int commentId){
        return commentService.getComment(commentId);
    }

    @PutMapping("/updateComments")
    public ResponseEntity update_Comment(@RequestBody ComUpdatePojo comUpdatePojo)
    {
        return commentService.update_comment(comUpdatePojo);
    }

    @DeleteMapping("/deleteComment")
    public ResponseEntity delete_comment(@RequestParam int commentId)
    {
        return commentService.delete_comment(commentId);
    }

    @PutMapping("/updateIsReported")
    public ResponseEntity update_isreported(@RequestParam int commentId,@RequestParam int reportedBy) { return commentService.update_isReported(commentId,reportedBy); }

}
