package com.quinbay.BlogService.services;


import com.quinbay.BlogService.api.BlogTagInterface;
import com.quinbay.BlogService.model.BlogTagRequest;
import com.quinbay.BlogService.model.BlogTags;
import com.quinbay.BlogService.repository.BlogTagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class BlogTagService implements BlogTagInterface {
    @Autowired
    BlogTagRepository blogTagRepository;
    @Autowired
    RestTemplate restTemplate;



    @Override
    public ArrayList<BlogTags> getBlogTags(int blogId){
        try {
            return blogTagRepository.findByBlogid(blogId);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

//    public ArrayList<BlogTags> getByTagId(int TagId){
//        return blogTagRepository.findByTagid(TagId);
//    }

    @Override
    public BlogTags addBlogTag(int blogId, BlogTagRequest blogTagRequest) {
        BlogTags blogTags=new BlogTags(blogId,blogTagRequest.getTagid(),blogTagRequest.getTagname());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(
                "http://localhost:8082/tag/updateNoOfBlogs?tagid="+blogTagRequest.getTagid()+"", HttpMethod.PUT, entity, ResponseEntity.class).getBody();
        return blogTagRepository.save(blogTags);
    }

    @Override
    public ResponseEntity deleteBlogTags(int blogId, int tagid) {
        try {
            blogTagRepository.deleteByBlogidAndTagid(blogId, tagid);
            return null;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
