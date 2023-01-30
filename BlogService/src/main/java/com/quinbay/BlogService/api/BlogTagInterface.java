package com.quinbay.BlogService.api;

import com.quinbay.BlogService.model.BlogTags;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BlogTagInterface {
    ArrayList<BlogTags> getBlogTags(int blogId);
    BlogTags add_blogTag(int blogId,int tagID);
    ResponseEntity deleteBlogTags(int blogId, int tagid);
}
