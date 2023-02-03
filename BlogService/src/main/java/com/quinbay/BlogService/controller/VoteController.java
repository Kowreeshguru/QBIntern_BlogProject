package com.quinbay.BlogService.controller;

import com.quinbay.BlogService.model.ParentType;
import com.quinbay.BlogService.model.VoteRequest;
import com.quinbay.BlogService.model.Votes;
import com.quinbay.BlogService.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/vote")
public class VoteController {
    @Autowired
    VoteService voteService;

    @GetMapping("/getVoteStatus")
    public Votes getVoteStatus(@RequestParam int votedfor, @RequestParam int userId, @RequestParam ParentType type){
        return voteService.getVotes(votedfor,userId,type);
    }

    @PostMapping("/addvote")
    public void addVotes(@RequestBody VoteRequest voteRequest)
    {
        voteService.addVote(voteRequest);
    }

}
