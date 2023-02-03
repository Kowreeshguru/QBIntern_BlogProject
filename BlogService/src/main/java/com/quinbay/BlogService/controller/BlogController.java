package com.quinbay.BlogService.controller;


import com.quinbay.BlogService.model.BlogRequest;
import com.quinbay.BlogService.model.Blogs;
import com.quinbay.BlogService.model.UpdateRequest;
import com.quinbay.BlogService.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value="/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping("/addBlogs")
    public Blogs addBlogs(@RequestBody BlogRequest blog)
    {
        return blogService.addTags(blog);
    }

    @GetMapping("/getBlogsById")
    public Blogs getBlogById(@RequestParam int blogId,@RequestParam int userId){
        return blogService.getBlogsById(blogId,userId);
    }

    @GetMapping("/getBlogsFromAnswer")
    public HashSet<Blogs> getBlogByAnsId(@RequestParam int userId){
        return blogService.getBlogsfromAnswer(userId);
    }

    @GetMapping("/getBlogsByTagId")
    public ArrayList<Blogs> getBlogBytagId(@RequestParam int tagId){
        return blogService.getBlogsByTags(tagId);
    }

    @GetMapping("/getBlogsByUserId")
    public List<Blogs> getBolgByUserId(@RequestParam int userId){
        return blogService.getBlogsByUserId(userId);
    }

    @GetMapping("/searchBlogs")
    public ArrayList<Blogs> searchBlogs(@RequestParam String hint){
        return blogService.searchBlogs("%"+hint+"%");
    }

    @GetMapping("/getReportedBlogs")
    public ArrayList<Blogs> getReportedblogs(){
        return blogService.getReportedblogs();
    }

    @GetMapping("/getBlogs")
    public ArrayList<Blogs> getBlogs(){
        return blogService.getBlogs();
    }


    @PutMapping("/updateBlogs")
    public ResponseEntity updateBlogs(@RequestBody UpdateRequest updateRequest)
    {
        return blogService.updateBlogs(updateRequest);
    }

//    @PutMapping("/updateUpvotes")
//    public ResponseEntity update_upvotes(@RequestParam int blogId,@RequestParam Boolean check) { return blogService.update_upvotes(blogId,check); }
//
//    @PutMapping("/updateDownvotes")
//    public ResponseEntity remove_downvotes(@RequestParam int blogId,@RequestParam Boolean check) { return blogService.update_downvotes(blogId,check); }
//
//    @PutMapping("/addView")
//    public ResponseEntity add_view(@RequestParam int blogId) { return blogService.add_views(blogId); }

    @PutMapping("/updateIsReported")
    public ResponseEntity updateIsreported(@RequestParam int blogId,@RequestParam int reportedBy) { return blogService.updateIsReported(blogId,reportedBy); }

    @PutMapping("/updateAcceptedAnswer")
    public ResponseEntity updateAcceptedAnswer(@RequestParam int blogId,@RequestParam int ansId) { return blogService.updateAcceptedAnswer(blogId,ansId); }

    @DeleteMapping("/deleteBlog")
    public ResponseEntity deleteBlogs(@RequestParam int blogid)
    {
        return blogService.deleteBlog(blogid);
    }
}
