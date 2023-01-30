package com.quinbay.BlogService.services;

import com.quinbay.BlogService.api.BlogInterface;
import com.quinbay.BlogService.model.BlogPojo;
import com.quinbay.BlogService.model.BlogTags;
import com.quinbay.BlogService.model.Blogs;
import com.quinbay.BlogService.model.UpdatePojo;
import com.quinbay.BlogService.repository.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class BlogService implements BlogInterface{
    @Autowired
    BlogRepository blogRepository;
    @Autowired
    BlogTagService blogTagService;


    @Override
    public Blogs add_tags(BlogPojo blogpojo) {
        try {
            Blogs blogs=new Blogs();
            blogs.setTitle(blogpojo.getTitle());
            blogs.setBlogdescription(blogpojo.getDescription());
            blogs.setThingstried(blogpojo.getThingsTried());
            blogs.setQuesraisedby(blogpojo.getPostedBy());
            return blogRepository.save(blogs);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Blogs get_blogs_byId(int blogId){
        try {
            return blogRepository.findByBlogidAndIsdeleted(blogId,false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Blogs> get_blogs(){
        try {
            return blogRepository.findByIsdeleted(false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<Blogs> get_blogsByTags(int tagId){

        ArrayList<Blogs> bloglist=new ArrayList<>();
        log.info("Into function");
        System.out.println(tagId);
        ArrayList<BlogTags> blogIdList=blogTagService.getByTagId(tagId);
        System.out.println(blogIdList);
        for(BlogTags blogTags:blogIdList) {
            try {
                bloglist.add(blogRepository.findByBlogidAndIsdeleted(blogTags.getBlogid(),false));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return bloglist;
    }

    @Override
    public ArrayList<Blogs> searchBlogs(String hint){
        try {
            return blogRepository.findByTitleLikeOrBlogdescriptionLike(hint,hint);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Blogs> get_reportedblogs(){
        try {
            return blogRepository.findByIsreportedAndIsdeleted(true,false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity<String> update_blogs(UpdatePojo updatePojo){
        try {
            Blogs blogs = blogRepository.findById(updatePojo.getBlogId());
            blogs.setTitle(updatePojo.getTitle());
            blogs.setBlogdescription(updatePojo.getDescription());
            blogs.setThingstried(updatePojo.getThingsTried());
            blogs.setUpdateby(updatePojo.getUpdatedBy());
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_upvotes(int blogId,Boolean check){
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

    public ResponseEntity<String> update_downvotes(int blogId,Boolean check){
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

    public ResponseEntity<String> add_views(int blogId){
        try {
            Blogs blogs = blogRepository.findById(blogId);
            blogs.setNoofviews(blogs.getNoofviews()+1);
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_isReported(int blogId,int reportedBy){
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

    public ResponseEntity<String> update_isClosed(int blogId){
        try {
            Blogs blogs = blogRepository.findById(blogId);
            blogs.setIsclosed(true);
            blogRepository.save(blogs);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity delete_blog(int blogId){
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
