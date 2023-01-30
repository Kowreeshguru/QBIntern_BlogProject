package com.quinbay.BlogService.services;


import com.quinbay.BlogService.api.BlogTagInterface;
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
    public ArrayList<BlogTags> getBlogTags(int blogId){return blogTagRepository.findByBlogid(blogId);}

    public ArrayList<BlogTags> getByTagId(int TagId){
        return blogTagRepository.findByTagid(TagId);
    }

    @Override
    public BlogTags add_blogTag(int blogId,int tagId) {
        BlogTags blogTags=new BlogTags();
        blogTags.setBlogid(blogId);
        blogTags.setTagid(tagId);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        restTemplate.exchange(
//                "http://localhost:8082/tag/updateNoOfBlogs?Tagid="+blogTags.getTagid()+"", HttpMethod.PUT, entity, ResponseEntity.class).getBody();
        return blogTagRepository.save(blogTags);
    }

    @Override
    public ResponseEntity deleteBlogTags(int blogId, int tagid) {
//        UserTag userTag=null;
        blogTagRepository.deleteByBlogidAndTagid(blogId, tagid);
//        System.out.println(userTag.getId());
//        if (userTag!=null) {
//            userTagRepository.deleteById(userTag.getId());
//            return new ResponseEntity("Successfully deleted",HttpStatus.OK);
//        }
//        else{return new ResponseEntity("",HttpStatus.BAD_REQUEST);}
        return null;
    }
}
