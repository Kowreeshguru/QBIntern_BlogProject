package com.quinbay.TagService.Interface;

import com.quinbay.TagService.Model.TagPojo;
import com.quinbay.TagService.Model.Tags;
import com.quinbay.TagService.Model.UpdatePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagInterface {
    Tags add_tags(TagPojo tags);
    Tags get_tags_byId(int tagId);
    List<Tags> get_tags();
    ResponseEntity<String> update_tags(UpdatePojo updatePojo);
    ResponseEntity update_NoOfBlog(int tagId);
    ResponseEntity update_NoOfClosedBlog(int tagId);
    ResponseEntity update_NoOfIntrested(int tagId);
    ResponseEntity delete_tag(int tagId);
}
