package com.quinbay.BlogService.services;

import com.quinbay.BlogService.api.BlogInterface;
import com.quinbay.BlogService.kafka.KafkaPublishingService;
import com.quinbay.BlogService.model.*;
import com.quinbay.BlogService.repository.BlogRepository;
import com.quinbay.BlogService.repository.BlogViewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class BlogService implements BlogInterface{
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    BlogViewRepository blogViewRepository;
    @Autowired
    BlogTagService blogTagService;
    @Autowired
    BlogViewService blogViewService;
    @Autowired
    KafkaPublishingService kafkaPublishingService;


    @Override
    public Blogs addTags(BlogRequest blogRequest) {
        try {
            TagTransfer tagList=new TagTransfer();
            ArrayList<Integer> ids=new ArrayList<>();
            Blogs blogs=new Blogs();
            blogs.setTitle(blogRequest.getTitle());
            blogs.setBlogdescription(blogRequest.getDescription());
            blogs.setThingstried(blogRequest.getThingsTried());
            blogs.setQuesraisedby(blogRequest.getPostedBy());
            blogs = blogRepository.save(blogs);
            for(BlogTagRequest blogTagRequest:blogRequest.getTagId()){
                ids.add(blogTagRequest.getTagid());
                blogTagService.addBlogTag(blogs.getBlogid(),blogTagRequest);
            }
            tagList.setTagList(ids);
            kafkaPublishingService.BlogTagInfo(tagList);
            return blogs;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Blogs getBlogsById(int blogId,int userId){
        try {
            Blogs blogs=blogRepository.findByBlogidAndIsdeleted(blogId,false);
            if(blogs !=null){
                System.out.println("inside getblog");
                BlogView blogView=new BlogView(blogId,userId);
                Boolean blogView1=blogViewService.addBlogView(blogView);
                System.out.println(blogView1);
//                if(blogView1){
//                    blogViewRepository.save(blogView);
//                    addViews(blogId);
//                }
            }
            return blogs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Blogs> getBlogs(){
        try {
            return blogRepository.findByIsdeleted(false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Blogs> getBlogsByTags(int tagId){
        try {
            return blogRepository.findByBlogtagsTagid(tagId);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public HashSet<Blogs> getBlogsfromAnswer(int userId){
//        ArrayList<Blogs> bloglist=new ArrayList<>();
//        ArrayList<Answers> answerList=answerService.getQuesId(userId);
//        System.out.println(answerList);
//        for(Answers ans:answerList) {
//            try {
//                bloglist.add(blogRepository.findByBlogidAndIsdeleted(ans.getAnsweredfor(),false));
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
        try {
            return blogRepository.findByAnswersAnsweredby(userId);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<Blogs> getBlogsByUserId(int userId){
        try {
            ArrayList<Blogs> blogs = blogRepository.findByQuesraisedbyAndIsdeleted(userId, false);
            return blogs;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Blogs> searchBlogs(String hint){
        try {
            return blogRepository.findByIsdeletedAndTitleLikeOrBlogdescriptionLike(false,hint,hint);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Blogs> getReportedblogs(){
        try {
            return blogRepository.findByIsreportedAndIsdeleted(true,false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity<String> updateBlogs(UpdateRequest updateRequest){
        try {
            Blogs blogs = blogRepository.findById(updateRequest.getBlogId());
            blogs.setTitle(updateRequest.getTitle());
            blogs.setBlogdescription(updateRequest.getDescription());
            blogs.setThingstried(updateRequest.getThingsTried());
            blogs.setUpdateby(updateRequest.getUpdatedBy());
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateUpvotes(int blogId,Boolean check){
        try {
            Blogs blogs = blogRepository.findById(blogId);
            if(check){blogs.setUpvotes(blogs.getUpvotes()+1);}
            else{blogs.setUpvotes(blogs.getUpvotes()-1);}
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateDownvotes(int blogId,Boolean check){
        try {
            Blogs blogs = blogRepository.findById(blogId);
            if(check){blogs.setDownvotes(blogs.getDownvotes()+1);}
            else{blogs.setDownvotes(blogs.getDownvotes()-1);}
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> addViews(int blogId){
        try {
            Blogs blogs = blogRepository.findById(blogId);
            blogs.setNoofviews(blogs.getNoofviews()+1);
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateIsReported(int blogId,int reportedBy){
        try {
            Blogs blogs = blogRepository.findById(blogId);
            blogs.setIsreported(true);
            blogs.setReportedby(reportedBy);
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateAcceptedAnswer(int blogId,int ansId){
        try {
            Blogs blogs = blogRepository.findById(blogId);
            blogs.setAcceptedanswer(ansId);
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity deleteBlog(int blogId){
        try {
            Blogs blog = blogRepository.findById(blogId);
            blog.setIsdeleted(true);
            blogRepository.save(blog);
            return new ResponseEntity("Successfully deleted",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity("Check your username and password",HttpStatus.BAD_REQUEST);
        }
    }
}
