package com.quinbay.user.service.api;

import com.quinbay.user.service.model.UserReq;
import com.quinbay.user.service.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface {
    Users add_user(UserReq userReq);
    Users get_user_byId(int userId);
    ResponseEntity user_login(String Email,String password);
    ResponseEntity delete_user(int userId);
    ResponseEntity<String> update_password(int id, String password);
    ResponseEntity<String> update_creditPoints(int userId,Boolean check);
}
