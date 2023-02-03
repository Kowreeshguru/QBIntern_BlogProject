package com.quinbay.BlogService.repository;

import com.quinbay.BlogService.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AnswerRepository extends JpaRepository<Answers, Integer> {
    ArrayList<Answers> findByAnsweredforAndIsdeleted(int BlogId,Boolean deleteCheck);
    ArrayList<Answers> findByAnsweredbyAndIsdeleted(int userId,Boolean deleteCheck);
    Answers findById(int ansId);
}
