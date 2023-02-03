package com.quinbay.user.service.controller;


import com.quinbay.user.service.model.UserRequest;
import com.quinbay.user.service.model.UserUpdateRequest;
import com.quinbay.user.service.model.Users;
import com.quinbay.user.service.model.LoginRequest;
import com.quinbay.user.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public Users addUser(@RequestBody UserRequest userRequest)
    {
        return userService.addUser(userRequest);
    }

    @GetMapping("/getUserById")
    public Users getUserById(@RequestParam int UserId){
        return userService.getUserById(UserId);
    }

    @PostMapping("/userLogin")
    public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest){
        return userService.userLogin(loginRequest);
    }

//    @GetMapping("/userEmail")
//    public void login(@RequestParam List<Integer> tagId){
//        userService.getEmail(tagId);
//    }

    @PutMapping("/updateUser")
    public ResponseEntity updateUser(@RequestBody UserUpdateRequest userUpdateRequest)
    {
        return userService.updateUser(userUpdateRequest);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity updatePassword(@RequestParam int UserId, @RequestParam String password)
    {
        return userService.updatePassword(UserId, password);
    }

    @PutMapping("/updateCreditpoints")
    public ResponseEntity updateCreditPoints(@RequestParam int UserId, @RequestParam Boolean check)
    {
        return userService.updateCreditPoints(UserId, check);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity deleteUser(@RequestParam int UserId) {
        return userService.deleteUser(UserId);

    }
}
