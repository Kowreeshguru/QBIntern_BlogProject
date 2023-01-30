package com.quinbay.BlogService.controller;


import com.quinbay.BlogService.model.BlogPojo;
import com.quinbay.BlogService.model.Blogs;
import com.quinbay.BlogService.model.UpdatePojo;
import com.quinbay.BlogService.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @PostMapping("/addBlogs")
    public Blogs add_blogs(@RequestBody BlogPojo blog)
    {
        return blogService.add_tags(blog);
    }

    @GetMapping("/getBlogsById")
    public Blogs get_blog_byId(@RequestParam int blogId){
        return blogService.get_blogs_byId(blogId);
    }

    @GetMapping("/getBlogsByTagId")
    public ArrayList<Blogs> get_blog_bytagId(@RequestParam int tagId){
        return blogService.get_blogsByTags(tagId);
    }

    @GetMapping("/searchBlogs")
    public ArrayList<Blogs> search_blogs(@RequestParam String hint){
        return blogService.searchBlogs("%"+hint+"%");
    }

    @GetMapping("/getReportedBlogs")
    public ArrayList<Blogs> get_reportedblogs(){
        return blogService.get_reportedblogs();
    }

    @GetMapping("/getBlogs")
    public ArrayList<Blogs> get_blogs(){
        return blogService.get_blogs();
    }


    @PutMapping("/updateBlogs")
    public ResponseEntity update_blogs(@RequestBody UpdatePojo updatePojo)
    {
        return blogService.update_blogs(updatePojo);
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
    public ResponseEntity update_isreported(@RequestParam int blogId,@RequestParam int reportedBy) { return blogService.update_isReported(blogId,reportedBy); }

    @PutMapping("/updateIsClosed")
    public ResponseEntity update_isclosed(@RequestParam int blogId) { return blogService.update_isClosed(blogId); }

    @DeleteMapping("/deleteBlog")
    public ResponseEntity delete_blogs(@RequestParam int blogid)
    {
        return blogService.delete_blog(blogid);
    }
}
