package com.quinbay.BlogService.repository;

import com.quinbay.BlogService.model.Comment;
import com.quinbay.BlogService.model.ParentType;
import com.quinbay.BlogService.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotesRepository extends JpaRepository<Votes, Integer> {
    Votes findByVotedforAndVotedbyAndParenttype(int Votedfor, int VotedBy, ParentType type);
//    void deleteByVotedforAndVotedby(int votedfor,int votedBy);
}
