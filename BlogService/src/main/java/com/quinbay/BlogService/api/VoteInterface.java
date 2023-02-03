package com.quinbay.BlogService.api;

import com.quinbay.BlogService.model.ParentType;
import com.quinbay.BlogService.model.VoteRequest;
import com.quinbay.BlogService.model.Votes;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteInterface {
    void addVote(VoteRequest voteRequest);
    Votes getVotes(int Votedfor, int userId, ParentType type);
}
