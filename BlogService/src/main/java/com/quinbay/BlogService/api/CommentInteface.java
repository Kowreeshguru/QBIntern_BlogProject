package com.quinbay.BlogService.api;


import com.quinbay.BlogService.model.ComUpdateRequest;
import com.quinbay.BlogService.model.Comment;
import com.quinbay.BlogService.model.CommentRequest;
import com.quinbay.BlogService.model.ParentType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommentInteface {
    Comment addComment(CommentRequest commentRequest);
    ArrayList<Comment> getComment(int commentId, ParentType type);
    ResponseEntity<String> updateComment(ComUpdateRequest comUpdateRequest);
    ResponseEntity deleteComment(int commentId);
    ResponseEntity<String> updateIsReported(int commentId,int reportedBy);
}
