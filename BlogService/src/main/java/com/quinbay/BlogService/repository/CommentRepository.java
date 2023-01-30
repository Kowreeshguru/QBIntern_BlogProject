package com.quinbay.BlogService.repository;
import com.quinbay.BlogService.model.Answers;
import com.quinbay.BlogService.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    ArrayList<Comment> findByCommentedforAndIsdeleted(int commmentId,Boolean check);
    Comment findById(int commentId);
}
