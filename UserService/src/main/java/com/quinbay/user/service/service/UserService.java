package com.quinbay.user.service.service;


import com.quinbay.user.service.api.UserInterface;
import com.quinbay.user.service.kafka.KafkaPublishingService;
import com.quinbay.user.service.model.*;
import com.quinbay.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserService implements UserInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UsersTagService usersTagService;
    @Autowired
    KafkaPublishingService kafkaPublishingService;

    @Override
    public Users addUser(UserRequest userRequest) {
        try {
            Users user = new Users();
            user.setUsername(userRequest.getUserName());
            user.setPassword(userRequest.getPassword());
            user.setEmail(userRequest.getEmail());
            user.setJobposition(userRequest.getJobPosition());
            user = userRepository.save(user);
            for(UserTagRequest userTag: userRequest.getTagId()){
                System.out.println("inside for");
                System.out.println(userTag);
                usersTagService.addUserTag(user.getUserid(),userTag);
            }
            return user;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Users getUserById(int userid){
        try {
            return userRepository.findByUseridAndIsdelete(userid,false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity<String> updateUser(UserUpdateRequest userUpdateRequest){
        try {
            int id=userUpdateRequest.getUserId();
            Users user = userRepository.findById(id);
            user.setUsername(userUpdateRequest.getUserName());
            user.setEmail(userUpdateRequest.getEmail());
            user.setJobposition(userUpdateRequest.getJobDescription());
            userRepository.save(user);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<String> updateCreditPoints(int userId,Boolean check){
        try {
            System.out.println(userId+" "+check);
            Users users = userRepository.findById(userId);
            if(check){users.setCreditpoints(users.getCreditpoints()+1);}
            else{users.setCreditpoints(users.getCreditpoints()-1);}
            userRepository.save(users);
            return new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updatePassword(int id, String password){
        try {
            Users user = userRepository.findByUseridAndIsdelete(id,false);
            user.setPassword(password);
            userRepository.save(user);
            return new ResponseEntity("Password Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<Users> userLogin(LoginRequest loginRequest){
        Users user=null;
        try {
            user=userRepository.findByEmailAndPasswordAndIsdelete(loginRequest.getEmail(),loginRequest.getPassword(),false);
            if(user!= null){
                return new ResponseEntity(user,HttpStatus.OK);
            }else{
                return new ResponseEntity("Check your Username and Password",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity deleteUser(int userId){
        try {
            Users user = userRepository.findById(userId);
            user.setIsdelete(true);
            userRepository.save(user);
            return new ResponseEntity("Successfully deleted",HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity("Check your id",HttpStatus.BAD_REQUEST);
        }
    }

    public void getEmail(List<Integer> tagList) {
        MailId mailId=new MailId();
        ArrayList<String> ids=new ArrayList<>();
        for(Users users:userRepository.findByUsertagsTagidIn(tagList)){
            ids.add(users.getEmail());
        }
        mailId.setMailidList(ids);
        kafkaPublishingService.MailTagInfo(mailId);
    }
}

