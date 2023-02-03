package com.quinbay.BlogService.services;


import com.quinbay.BlogService.api.VoteInterface;
import com.quinbay.BlogService.model.*;
import com.quinbay.BlogService.repository.VotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class VoteService implements VoteInterface {

    @Autowired
    VotesRepository votesRepository;
    @Autowired
    BlogService blogService;
    @Autowired
    AnswerService answerService;
    @Autowired
    CommentService commentService;


    public void setUpvotes(VoteRequest voteRequest, Boolean voteType){
        if(voteRequest.getParenttype()==ParentType.BLOG){blogService.updateUpvotes(voteRequest.getVotedfor(),voteType);}
        else if(voteRequest.getParenttype()==ParentType.ANSWER){answerService.updateUpvotes(voteRequest.getVotedfor(),voteType);}
        else if(voteRequest.getParenttype()==ParentType.COMMENT){commentService.updateUpvotes(voteRequest.getVotedfor(),voteType);}
    }

    public void setDownvotes(VoteRequest voteRequest, Boolean voteType){
        if(voteRequest.getParenttype()==ParentType.BLOG){blogService.updateDownvotes(voteRequest.getVotedfor(),voteType);}
        else if(voteRequest.getParenttype()==ParentType.ANSWER){answerService.updateDownvotes(voteRequest.getVotedfor(),voteType);}
        else if(voteRequest.getParenttype()==ParentType.COMMENT){commentService.updateDownvotes(voteRequest.getVotedfor(),voteType);}
    }

    @Override
    public void addVote(VoteRequest voteRequest) {
        try {
            if(voteRequest.getType() == Type.UP){
                Votes votes1=votesRepository.findByVotedforAndVotedbyAndParenttype(voteRequest.getVotedfor(), voteRequest.getVoteby(), voteRequest.getParenttype());
                if(votes1 == null) {
                    Votes votes = new Votes(voteRequest.getVoteby(),true,voteRequest.getParenttype(),voteRequest.getVotedfor());
                    votesRepository.save(votes);
                    setUpvotes(voteRequest, true);
                }
            }
            else if(voteRequest.getType() == Type.DOWN){
                Votes votes1=votesRepository.findByVotedforAndVotedbyAndParenttype(voteRequest.getVotedfor(), voteRequest.getVoteby(), voteRequest.getParenttype());
                if(votes1 == null) {
                    Votes votes = new Votes(voteRequest.getVoteby(),false,voteRequest.getParenttype(),voteRequest.getVotedfor());
                    votesRepository.save(votes);
                    setDownvotes(voteRequest, true);
                }
            }
            else if(voteRequest.getType() == Type.CLEARUP){
                Votes votes=votesRepository.findByVotedforAndVotedbyAndParenttype(voteRequest.getVotedfor(), voteRequest.getVoteby(), voteRequest.getParenttype());
                votesRepository.deleteById(votes.getId());
                setUpvotes(voteRequest,false);
            }
            else if(voteRequest.getType() == Type.CLEARDOWN){
                Votes votes=votesRepository.findByVotedforAndVotedbyAndParenttype(voteRequest.getVotedfor(), voteRequest.getVoteby(),voteRequest.getParenttype());
                votesRepository.deleteById(votes.getId());
                setDownvotes(voteRequest,false);
            }
            else if(voteRequest.getType() == Type.UP_DOWN){
                Votes vote=votesRepository.findByVotedforAndVotedbyAndParenttype(voteRequest.getVotedfor(), voteRequest.getVoteby(),voteRequest.getParenttype());
                votesRepository.deleteById(vote.getId());
                Votes votes = new Votes(voteRequest.getVoteby(),false,voteRequest.getParenttype(),voteRequest.getVotedfor());
                votesRepository.save(votes);
                setUpvotes(voteRequest,false);setDownvotes(voteRequest,true);
            }
            else if(voteRequest.getType() == Type.DOWN_UP){
                Votes vote=votesRepository.findByVotedforAndVotedbyAndParenttype(voteRequest.getVotedfor(), voteRequest.getVoteby(),voteRequest.getParenttype());
                votesRepository.deleteById(vote.getId());
                Votes votes = new Votes(voteRequest.getVoteby(),true,voteRequest.getParenttype(),voteRequest.getVotedfor());
                votesRepository.save(votes);
                setUpvotes(voteRequest,true);setDownvotes(voteRequest,false);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public Votes getVotes(int votedfor,int userId, ParentType type){
        try {
            return votesRepository.findByVotedforAndVotedbyAndParenttype(votedfor, userId, type);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
