package com.quinbay.BlogService.controller;

import com.quinbay.BlogService.model.VotePojo;
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
    public Votes get_voteStatus(@RequestParam int votedfor,@RequestParam int userId){
        return voteService.getVotes(votedfor,userId);
    }

    @PostMapping("/addvote")
    public void add_votes(@RequestBody VotePojo votePojo)
    {
        voteService.add_vote(votePojo);
    }

}
