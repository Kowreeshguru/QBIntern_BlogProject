package com.quinbay.TagService.Controller;


import com.quinbay.TagService.Model.TagPojo;
import com.quinbay.TagService.Model.Tags;
import com.quinbay.TagService.Model.UpdatePojo;
import com.quinbay.TagService.Service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @PostMapping("/addTags")
    public Tags add_tags(@RequestBody TagPojo tagPojo)
    {
        return tagService.add_tags(tagPojo);
    }

    @GetMapping("/getTagsById")
    public Tags get_tag_byId(@RequestParam int tagId){
        return tagService.get_tags_byId(tagId);
    }

    @GetMapping("/getTag")
    public ArrayList<Tags> get_tag(){
        return tagService.get_tags();
    }

    @PutMapping("/updateTags")
    public ResponseEntity update_user(@RequestBody UpdatePojo updatePojo)
    {
        return tagService.update_tags(updatePojo);
    }

    @PutMapping("/updateNoOfBlogs")
    public ResponseEntity update_NoOfBlogs(@RequestParam int Tagid)
    {
        return tagService.update_NoOfBlog(Tagid);
    }

    @PutMapping("/updateNoOfClosedBlogs")
    public ResponseEntity update_NoOfClosedBlogs(@RequestParam int Tagid)
    {
        return tagService.update_NoOfClosedBlog(Tagid);
    }

    @PutMapping("/updateNoOfIntrested")
    public ResponseEntity update_NoOfIntrested(@RequestParam int Tagid)
    {
        return tagService.update_NoOfIntrested(Tagid);
    }

    @DeleteMapping("/deleteTag")
    public ResponseEntity delete_tags(@RequestParam int Tagid)
    {
        return tagService.delete_tag(Tagid);
    }
}
