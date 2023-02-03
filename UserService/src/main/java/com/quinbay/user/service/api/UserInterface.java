package com.quinbay.user.service.api;

import com.quinbay.user.service.model.LoginRequest;
import com.quinbay.user.service.model.UserRequest;
import com.quinbay.user.service.model.UserUpdateRequest;
import com.quinbay.user.service.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterface {
    Users addUser(UserRequest userRequest);
    Users getUserById(int userId);
    ResponseEntity userLogin(LoginRequest loginRequest);
    ResponseEntity deleteUser(int userId);
    ResponseEntity<String> updateUser(UserUpdateRequest userUpdateRequest);
    ResponseEntity<String> updatePassword(int id, String password);
    ResponseEntity<String> updateCreditPoints(int userId,Boolean check);
}
