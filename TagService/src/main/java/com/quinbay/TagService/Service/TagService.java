package com.quinbay.TagService.Service;


import com.quinbay.TagService.Interface.TagInterface;
import com.quinbay.TagService.Model.TagPojo;
import com.quinbay.TagService.Model.Tags;
import com.quinbay.TagService.Model.UpdatePojo;
import com.quinbay.TagService.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService implements TagInterface {

    @Autowired
    TagRepository tagRepository;

    @Override
    public Tags add_tags(TagPojo tagPojo) {
        try {
            Tags tags=new Tags();
            tags.setTagname(tagPojo.getTagName());
            tags.setTagdescription(tagPojo.getTagDescription());
            tags.setCreatedby(tagPojo.getCreatedby());
            return tagRepository.save(tags);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Tags get_tags_byId(int tagId){
        try {
            return tagRepository.findById(tagId);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<Tags> get_tags(){
        try {
            return tagRepository.findByIsdeleted(false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity<String> update_tags(UpdatePojo updatePojo){
        try {
            Tags tags = tagRepository.findById(updatePojo.getTagId());
            tags.setTagname(updatePojo.getTagName());
            tags.setTagdescription(updatePojo.getTagDescription());
            tags.setUpdatedby(updatePojo.getUpdatedby());
            tagRepository.save(tags);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity update_NoOfBlog(int tagId){
        try {
            Tags tags = tagRepository.findById(tagId);
            tags.setNoofblogs(tags.getNoofblogs()+1);
            tagRepository.save(tags);
            return new ResponseEntity("NoOfBlog added",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity update_NoOfClosedBlog(int tagId){
        try {
            Tags tags = tagRepository.findById(tagId);
            tags.setNoofclosedblogs(tags.getNoofclosedblogs()+1);
            tagRepository.save(tags);
            return new ResponseEntity("NoOfClosedBlog added",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity update_NoOfIntrested(int tagId){
        try {
            Tags tags = tagRepository.findById(tagId);
            tags.setNoofintrestedusers(tags.getNoofintrestedusers()+1);
            tagRepository.save(tags);
            return new ResponseEntity("NoOfIntrested added",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity delete_tag(int tagId){
        try {
            Tags tags = tagRepository.findById(tagId);
            tags.setIsdeleted(true);
            tagRepository.save(tags);
            return new ResponseEntity("Successfully deleted",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity("Check your username and password",HttpStatus.BAD_REQUEST);
        }
    }
}
