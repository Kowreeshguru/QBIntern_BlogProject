package com.quinbay.user.service.api;


import com.quinbay.user.service.model.UserTag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagInterface {

    UserTag add_userTag(UserTag userTag);

    ResponseEntity deleteUserTags(int userid,int tagid);
}
