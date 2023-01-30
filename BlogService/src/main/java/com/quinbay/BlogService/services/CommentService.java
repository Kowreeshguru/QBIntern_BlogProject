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
    public Comment add_comment(CommentPojo commentPojo) {
        try {
            Comment comment=new Comment();
            comment.setContent(commentPojo.getContent());
            comment.setCommentedfor(commentPojo.getCommentFor());
            comment.setCommentedby(commentPojo.getCommentedby());
            return commentRepository.save(comment);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Comment> getComment(int commentId){return commentRepository.findByCommentedforAndIsdeleted(commentId,false);}

    @Override
    public ResponseEntity<String> update_comment(ComUpdatePojo comUpdatePojo){
        try {
            Comment comment = commentRepository.findById(comUpdatePojo.getAnsId());
            comment.setContent(comUpdatePojo.getContent());
            comment.setUpdatedby(comUpdatePojo.getUpdatedBy());
            commentRepository.save(comment);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_upvotes(int commentId,Boolean check){
        try {
            Comment comment = commentRepository.findById(commentId);
            if(check){comment.setUpvotes(comment.getUpvotes()+1);}
            else{comment.setUpvotes(comment.getUpvotes()-1);}
            commentRepository.save(comment);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_downvotes(int commentId,Boolean check){
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
    public ResponseEntity<String> update_isReported(int commentId,int reportedBy){
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
    public ResponseEntity delete_comment(int commentId){
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
