package com.quinbay.TagService.Controller;


import com.quinbay.TagService.Model.TagRequest;
import com.quinbay.TagService.Model.Tags;
import com.quinbay.TagService.Model.UpdateRequest;
import com.quinbay.TagService.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping("/addTags")
    public Tags addTags(@RequestBody TagRequest tagRequest)
    {
        return tagService.addTags(tagRequest);
    }

//    @Cacheable(value = "listOfTags")
    @GetMapping("/getTagsById")
    public Tags getTagById(@RequestParam int tagId){
        return tagService.getTagsById(tagId);
    }

    @GetMapping("/getTag")
    public ArrayList<Tags> getTag(){
        return tagService.getTags();
    }

    @PutMapping("/updateTags")
    public ResponseEntity updateUser(@RequestBody UpdateRequest updateRequest)
    {
        return tagService.updateTags(updateRequest);
    }

    @PutMapping("/updateNoOfBlogs")
    public ResponseEntity updateNoOfBlogs(@RequestParam int tagid)
    {
        return tagService.updateNoOfBlog(tagid);
    }

    @PutMapping("/updateNoOfClosedBlogs")
    public ResponseEntity updateNoOfClosedBlogs(@RequestParam int tagid)
    {
        return tagService.updateNoOfClosedBlog(tagid);
    }

    @PutMapping("/updateNoOfIntrested")
    public ResponseEntity updateNoOfIntrested(@RequestParam int tagid)
    {
        return tagService.updateNoOfIntrested(tagid);
    }

    @DeleteMapping("/deleteTag")
    public ResponseEntity deletetags(@RequestParam int tagid)
    {
        return tagService.deleteTag(tagid);
    }
}
