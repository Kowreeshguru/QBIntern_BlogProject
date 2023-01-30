package com.quinbay.BlogService.services;

import com.quinbay.BlogService.api.BlogViewInterface;
import com.quinbay.BlogService.model.BlogView;
import com.quinbay.BlogService.repository.BlogViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class BlogViewService implements BlogViewInterface {

    @Autowired
    BlogViewRepository blogViewRepository;
    @Autowired
    BlogService blogService;

    @Override
    public BlogView add_blogView(BlogView blogView) {
        BlogView blogView1=null;
        blogView1=blogViewRepository.findByBlogidAndUserid(blogView.getBlogid(),blogView.getUserid());
        if(blogView1 == null){
            blogService.add_views(blogView.getBlogid());
            return blogViewRepository.save(blogView);
        }else{return null;}
    }
}
