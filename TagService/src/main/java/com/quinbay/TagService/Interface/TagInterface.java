package com.quinbay.TagService.Interface;

import com.quinbay.TagService.Model.TagRequest;
import com.quinbay.TagService.Model.Tags;
import com.quinbay.TagService.Model.UpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagInterface {
    Tags addTags(TagRequest tags);
    Tags getTagsById(int tagId);
    List<Tags> getTags();
    ResponseEntity<String> updateTags(UpdateRequest updateRequest);
    ResponseEntity updateNoOfBlog(int tagId);
    ResponseEntity updateNoOfClosedBlog(int tagId);
    ResponseEntity updateNoOfIntrested(int tagId);
    ResponseEntity deleteTag(int tagId);
}
