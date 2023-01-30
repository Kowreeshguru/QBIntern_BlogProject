package com.quinbay.BlogService.services;


import com.quinbay.BlogService.api.VoteInterface;
import com.quinbay.BlogService.model.*;
import com.quinbay.BlogService.repository.CommentRepository;
import com.quinbay.BlogService.repository.VotesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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

    @Override
    public void add_vote(VotePojo votePojo) {
        try {
            if(votePojo.getType() == Type.UP){
                Votes votes=new Votes();
                votes.setVotedby(votePojo.getVoteby());
                votes.setVotetype(true);
                votes.setVotedfor(votePojo.getVotedfor());
                votesRepository.save(votes);
                if(votePojo.getParenttype()==ParentType.BLOG){blogService.update_upvotes(votePojo.getVotedfor(),true);}
                else if(votePojo.getParenttype()==ParentType.ANSWER){answerService.update_upvotes(votePojo.getVotedfor(),true);}
                else if(votePojo.getParenttype()==ParentType.COMMENT){commentService.update_upvotes(votePojo.getVotedfor(),true);}
            }
            else if(votePojo.getType() == Type.DOWN){
                Votes votes=new Votes();
                votes.setVotedby(votePojo.getVoteby());
                votes.setVotetype(false);
                votes.setVotedfor(votePojo.getVotedfor());
                votesRepository.save(votes);
                if(votePojo.getParenttype()==ParentType.BLOG){blogService.update_downvotes(votePojo.getVotedfor(),true);}
                else if(votePojo.getParenttype()==ParentType.ANSWER){answerService.update_downvotes(votePojo.getVotedfor(),true);}
                else if(votePojo.getParenttype()==ParentType.COMMENT){commentService.update_downvotes(votePojo.getVotedfor(),true);}
            }
            else if(votePojo.getType() == Type.CLEARUP){
                Votes votes=votesRepository.findByVotedforAndVotedby(votePojo.getVotedfor(),votePojo.getVoteby());
                votesRepository.deleteById(votes.getId());
                if(votePojo.getParenttype()==ParentType.BLOG){blogService.update_upvotes(votePojo.getVotedfor(),false);}
                else if(votePojo.getParenttype()==ParentType.ANSWER){answerService.update_upvotes(votePojo.getVotedfor(),false);}
                else if(votePojo.getParenttype()==ParentType.COMMENT){commentService.update_upvotes(votePojo.getVotedfor(),false);}
            }
            else if(votePojo.getType() == Type.CLEARDOWN){
                Votes votes=votesRepository.findByVotedforAndVotedby(votePojo.getVotedfor(),votePojo.getVoteby());
                votesRepository.deleteById(votes.getId());
                if(votePojo.getParenttype()==ParentType.BLOG){blogService.update_downvotes(votePojo.getVotedfor(),false);}
                else if(votePojo.getParenttype()==ParentType.ANSWER){answerService.update_downvotes(votePojo.getVotedfor(),false);}
                else if(votePojo.getParenttype()==ParentType.COMMENT){commentService.update_downvotes(votePojo.getVotedfor(),false);}
            }
            else if(votePojo.getType() == Type.UP_DOWN){
                Votes vote=votesRepository.findByVotedforAndVotedby(votePojo.getVotedfor(),votePojo.getVoteby());
                votesRepository.deleteById(vote.getId());
                Votes votes=new Votes();
                votes.setVotedby(votePojo.getVoteby());
                votes.setVotetype(false);
                votes.setVotedfor(votePojo.getVotedfor());
                votesRepository.save(votes);
                if(votePojo.getParenttype()==ParentType.BLOG){blogService.update_upvotes(votePojo.getVotedfor(),false);blogService.update_downvotes(votePojo.getVotedfor(),true);}
                else if(votePojo.getParenttype()==ParentType.ANSWER){answerService.update_upvotes(votePojo.getVotedfor(),false);answerService.update_downvotes(votePojo.getVotedfor(),true);}
                else if(votePojo.getParenttype()==ParentType.COMMENT){commentService.update_upvotes(votePojo.getVotedfor(),false);commentService.update_downvotes(votePojo.getVotedfor(),true);}
            }
            else if(votePojo.getType() == Type.DOWN_UP){
                Votes vote=votesRepository.findByVotedforAndVotedby(votePojo.getVotedfor(),votePojo.getVoteby());
                votesRepository.deleteById(vote.getId());
                Votes votes=new Votes();
                votes.setVotedby(votePojo.getVoteby());
                votes.setVotetype(true);
                votes.setVotedfor(votePojo.getVotedfor());
                votesRepository.save(votes);
                if(votePojo.getParenttype()==ParentType.BLOG){blogService.update_upvotes(votePojo.getVotedfor(),true);blogService.update_downvotes(votePojo.getVotedfor(),false);}
                else if(votePojo.getParenttype()==ParentType.ANSWER){answerService.update_upvotes(votePojo.getVotedfor(),true);answerService.update_downvotes(votePojo.getVotedfor(),false);}
                else if(votePojo.getParenttype()==ParentType.COMMENT){commentService.update_upvotes(votePojo.getVotedfor(),true);commentService.update_downvotes(votePojo.getVotedfor(),false);}
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public Votes getVotes(int Votedfor,int userId){return votesRepository.findByVotedforAndVotedby(Votedfor,userId);}
}
