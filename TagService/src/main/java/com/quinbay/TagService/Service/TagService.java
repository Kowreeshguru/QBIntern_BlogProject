package com.quinbay.TagService.Service;


import com.quinbay.TagService.Interface.TagInterface;
import com.quinbay.TagService.Model.TagRequest;
import com.quinbay.TagService.Model.Tags;
import com.quinbay.TagService.Model.UpdateRequest;
import com.quinbay.TagService.Repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TagService implements TagInterface {

    @Autowired
    TagRepository tagRepository;

    @Override
    public Tags addTags(TagRequest tagRequest) {
        try {
            Tags tags=new Tags();
            tags.setTagname(tagRequest.getTagName());
            tags.setTagdescription(tagRequest.getTagDescription());
            tags.setCreatedby(tagRequest.getCreatedby());
            return tagRepository.save(tags);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Tags getTagsById(int tagId){
        try {
            return tagRepository.findById(tagId).get();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

//    @Cacheable(value = "listOfCategories")
    @Override
    public ArrayList<Tags> getTags(){
        try {
            return tagRepository.findByIsdeleted(false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity<String> updateTags(UpdateRequest updateRequest){
        try {
            Optional<Tags> tags = tagRepository.findById(updateRequest.getTagId());
            tags.get().setTagname(updateRequest.getTagName());
            tags.get().setTagdescription(updateRequest.getTagDescription());
            tags.get().setUpdatedby(updateRequest.getUpdatedby());
            tagRepository.save(tags.get());
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateNoOfBlog(int tagId){
        try {
            Optional<Tags> tags = tagRepository.findById(tagId);
            tags.get().setNoofblogs(tags.get().getNoofblogs()+1);
            tagRepository.save(tags.get());
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateNoOfClosedBlog(int tagId){
        try {
            Optional<Tags> tags = tagRepository.findById(tagId);
            tags.get().setNoofclosedblogs(tags.get().getNoofclosedblogs()+1);
            tagRepository.save(tags.get());
            return new ResponseEntity("NoOfClosedBlog added",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity updateNoOfIntrested(int tagId){
        try {
            System.out.println(tagId);
            Optional<Tags> tags = tagRepository.findById(tagId);
            tags.get().setNoofintrestedusers(tags.get().getNoofintrestedusers()+1);
            tagRepository.save(tags.get());
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity deleteTag(int tagId){
        try {
            Optional<Tags> tags = tagRepository.findById(tagId);
            tags.get().setIsdeleted(true);
            tagRepository.save(tags.get());
            return new ResponseEntity("Successfully deleted",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity("Check your username and password",HttpStatus.BAD_REQUEST);
        }
    }
}
