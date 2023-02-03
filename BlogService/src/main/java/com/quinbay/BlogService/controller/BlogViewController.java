package com.quinbay.BlogService.controller;

import com.quinbay.BlogService.model.BlogTags;
import com.quinbay.BlogService.model.BlogView;
import com.quinbay.BlogService.services.BlogService;
import com.quinbay.BlogService.services.BlogViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/blogView")
public class BlogViewController {

    @Autowired
    BlogViewService blogViewService;

//    @PostMapping("/addBlogTag")
//    public BlogView addBlogView(@RequestBody BlogView blogView)
//    {
//        return blogViewService.addBlogView(blogView);
//    }
}
