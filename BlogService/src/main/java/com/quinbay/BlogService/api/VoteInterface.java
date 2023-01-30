package com.quinbay.BlogService.api;

import com.quinbay.BlogService.model.VotePojo;
import com.quinbay.BlogService.model.Votes;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface VoteInterface {
    void add_vote(VotePojo votePojo);
    Votes getVotes(int Votedfor,int userId);
}
