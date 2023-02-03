package com.quinbay.BlogService.services;

import com.quinbay.BlogService.api.CommentInteface;
import com.quinbay.BlogService.model.*;
import com.quinbay.BlogService.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CommentService implements CommentInteface {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment addComment(CommentRequest commentRequest) {
        try {
            Comment comment=new Comment();
            comment.setContent(commentRequest.getContent());
            comment.setCommentedfor(commentRequest.getCommentFor());
            comment.setType(commentRequest.getType());
            comment.setCommentedby(commentRequest.getCommentedby());
            return commentRepository.save(comment);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Comment> getComment(int commentId,ParentType type){
        try {
            return commentRepository.findByCommentedforAndTypeAndIsdeleted(commentId, type, false);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity<String> updateComment(ComUpdateRequest commentUpdateRequest){
        try {
            Comment comment = commentRepository.findById(commentUpdateRequest.getAnsId());
            comment.setContent(commentUpdateRequest.getContent());
            comment.setUpdatedby(commentUpdateRequest.getUpdatedBy());
            commentRepository.save(comment);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateUpvotes(int commentId,Boolean check){
        try {
            System.out.println("inside comment updateupvotes"+commentId);
            Comment comment = commentRepository.findById(commentId);
            if(check){comment.setUpvotes(comment.getUpvotes()+1);}
            else{comment.setUpvotes(comment.getUpvotes()-1);}
            commentRepository.save(comment);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateDownvotes(int commentId,Boolean check){
        try {
            Comment comment = commentRepository.findById(commentId);
            if(check){comment.setDownvotes(comment.getDownvotes()+1);}
            else{comment.setDownvotes(comment.getDownvotes()-1);}
            commentRepository.save(comment);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateIsReported(int commentId,int reportedBy){
        try {
            Comment comment = commentRepository.findById(commentId);
            comment.setIsreported(true);
            comment.setReportedby(reportedBy);
            commentRepository.save(comment);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity deleteComment(int commentId){
        try {
            Comment comment = commentRepository.findById(commentId);
            comment.setIsdeleted(true);
            commentRepository.save(comment);
            return new ResponseEntity("Successfully deleted",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity("Check your username and password",HttpStatus.BAD_REQUEST);
        }
    }

}
