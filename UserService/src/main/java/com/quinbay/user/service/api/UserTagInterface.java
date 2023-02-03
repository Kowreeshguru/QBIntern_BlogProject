package com.quinbay.user.service.api;


import com.quinbay.user.service.model.UserTag;
import com.quinbay.user.service.model.UserTagRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagInterface {

    UserTag addUserTag(int userId,UserTagRequest tagId);
    ResponseEntity deleteUserTags(int userid,int tagid);
}
