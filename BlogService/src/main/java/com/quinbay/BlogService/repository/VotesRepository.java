package com.quinbay.BlogService.repository;

import com.quinbay.BlogService.model.Comment;
import com.quinbay.BlogService.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Integer> {
    Votes findByVotedforAndVotedby(int Votedfor,int VotedBy);
    void deleteByVotedforAndVotedby(int votedfor,int votedBy);
}
