package com.quinbay.user.service.controller;


import com.quinbay.user.service.model.UserReq;
import com.quinbay.user.service.model.Users;
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
    public Users addUser(@RequestBody UserReq userReq)
    {
        return userService.add_user(userReq);
    }

    @GetMapping("/getUserById")
    public Users get_user_byId(@RequestParam int UserId){
        return userService.get_user_byId(UserId);
    }

    @GetMapping("/login")
    public ResponseEntity<Users> login(@RequestParam String Email,@RequestParam String password){
        return userService.user_login(Email,password);
    }

    @PutMapping("/updateUser")
    public ResponseEntity update_user(@RequestParam int UserId, @RequestParam String Username,@RequestParam String EmailId,@RequestParam String JobPosition)
    {
        return userService.update_user(UserId, Username,EmailId,JobPosition);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity update_password(@RequestParam int UserId, @RequestParam String password)
    {
        return userService.update_password(UserId, password);
    }

    @PutMapping("/updateCreditpoints")
    public ResponseEntity update_creditPoints(@RequestParam int UserId, @RequestParam Boolean check)
    {
        return userService.update_creditPoints(UserId, check);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity delete_user(@RequestParam int UserId) {
        return userService.delete_user(UserId);

    }
}
