package com.quinbay.user.service.controller;


import com.quinbay.user.service.model.TagTransfer;
import com.quinbay.user.service.model.UserTag;
import com.quinbay.user.service.service.UsersTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/userTag")
public class UserTagController {

    @Autowired
    UsersTagService usersTagService;

    @GetMapping("/getUserTag")
    public ArrayList<UserTag> getUserTags(@RequestParam int UserId){
        return usersTagService.getUserTags(UserId);
    }

//    @PostMapping("/addUserTag")
//    public UserTag addUserTag(@RequestBody UserTag a)
//    {
//
//        return usersTagService.addUserTag(a);
//    }

//    @GetMapping("/mailService")
//    public void mailService(@RequestBody TagTransfer tagTransfer)
//    {
//        System.out.println(tagTransfer.getTagList());
//        usersTagService.mail_service(tagList);
//    }

    @DeleteMapping("/deleteUserTag")
    public ResponseEntity deleteUserTags(@RequestParam int userid, @RequestParam int tagid){
        return usersTagService.deleteUserTags(userid,tagid);
    }
}
