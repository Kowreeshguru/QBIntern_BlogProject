package com.quinbay.BlogService.api;

import com.quinbay.BlogService.model.BlogTagRequest;
import com.quinbay.BlogService.model.BlogTags;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BlogTagInterface {
    ArrayList<BlogTags> getBlogTags(int blogId);
    BlogTags addBlogTag(int blogId, BlogTagRequest blogTagRequest);
    ResponseEntity deleteBlogTags(int blogId, int tagid);
}
