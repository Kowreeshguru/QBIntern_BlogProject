package com.quinbay.BlogService.controller;


import com.quinbay.BlogService.kafka.KafkaPublishingService;
import com.quinbay.BlogService.model.BlogTags;
import com.quinbay.BlogService.model.TagTransfer;
import com.quinbay.BlogService.services.BlogService;
import com.quinbay.BlogService.services.BlogTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping(value="/blogTag")
public class BlogTagController {
    @Autowired
    BlogTagService blogTagService;
    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    TagTransfer tagTransfer;
    @Autowired
    KafkaPublishingService kafkaPublishingService;

    @GetMapping("/getBlogTag")
    public ArrayList<BlogTags> getBlogTags(@RequestParam int blogId){
        return blogTagService.getBlogTags(blogId);
    }

//    @PostMapping("/addBlogTag")
//    public BlogTags addBlogTag(@RequestBody BlogTagPojo blogTagPojo)
//    {
//        for(Integer tagId:blogTagPojo.getTagList()) {
//            blogTagService.addBlogTag(blogTagPojo.getBlogId(),tagId);
//        }
//        TagTransfer tagTransfer=new TagTransfer();
//        tagTransfer.setTagList(blogTagPojo.getTagList());
////        TagTransfer tagTransfer=new TagTransfer();
////        tagTransfer.setTagList(blogTagPojo.getTagList());
//        kafkaPublishingService.BlogTagInfo(tagTransfer);
//////        System.out.println(blogTagPojo.getTagList().getClass());
//
////        tagTransfer.setTagList(blogTagPojo.getTagList());
////        HttpHeaders headers = new HttpHeaders();
////        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////        HttpEntity<TagTransfer> entity = new HttpEntity<>(tagTransfer,headers);
////        restTemplate.exchange(
////                "http://localhost:8080/userTag/mailService", HttpMethod.GET, entity, ArrayList.class).getBody();
//        return null;
//    }
    @DeleteMapping("/deleteBlogTag")
    public ResponseEntity deleteBlogTags(@RequestParam int blogId, @RequestParam int tagid){
        return blogTagService.deleteBlogTags(blogId,tagid);
    }
}
