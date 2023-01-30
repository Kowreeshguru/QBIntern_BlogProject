package com.quinbay.BlogService.api;


import com.quinbay.BlogService.model.ComUpdatePojo;
import com.quinbay.BlogService.model.Comment;
import com.quinbay.BlogService.model.CommentPojo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentInteface {
    Comment add_comment(CommentPojo commentPojo);
    ArrayList<Comment> getComment(int commentId);
    ResponseEntity<String> update_comment(ComUpdatePojo comUpdatePojo);
    ResponseEntity delete_comment(int commentId);
    ResponseEntity<String> update_isReported(int commentId,int reportedBy);
}
