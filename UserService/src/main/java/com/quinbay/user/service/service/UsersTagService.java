package com.quinbay.user.service.service;


import com.quinbay.user.service.api.UserTagInterface;
import com.quinbay.user.service.kafka.KafkaPublishingService;
import com.quinbay.user.service.model.MailId;
import com.quinbay.user.service.model.UserTag;
import com.quinbay.user.service.model.UserTagRequest;
import com.quinbay.user.service.repository.UsersTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UsersTagService implements UserTagInterface {
    @Autowired
    UsersTagRepository userTagRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    KafkaPublishingService kafkaPublishingService;

    public ArrayList<UserTag> getUserTags(int userId) {
        try {
            return userTagRepository.findByUserid(userId);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public UserTag addUserTag(int userId, UserTagRequest tagId) {
        try {
            UserTag userTag = new UserTag();
            userTag.setUserid(userId);
            userTag.setTagid(tagId.getTagid());
            userTag.setTagname(tagId.getTagname());
            System.out.println(userTag);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(
                    "http://localhost:8082/tag/updateNoOfIntrested?tagid=" +tagId.getTagid()+ "", HttpMethod.PUT, entity, ResponseEntity.class);
            return userTagRepository.save(userTag);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity deleteUserTags(int userid, int tagid) {
        try {
            userTagRepository.deleteByUseridAndTagid(userid, tagid);
            return new ResponseEntity("Deleted sucessfully", HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity("Gone wrong", HttpStatus.BAD_REQUEST);
        }
    }

//    public void mail_service(ArrayList<Integer> tagList) {
//        HashSet<Integer> userList=new HashSet<>();
////        System.out.println("inside fucntion");
////        System.out.println(tagList);
//        for(Integer tagId:tagList){
////            System.out.println("Inside loop");
//            ArrayList<UserTag> userlist = userTagRepository.findByTagid(tagId);
//            for(UserTag tagid:userlist){
////                System.out.println("inside second");
//                userList.add(tagid.getUserid());
////                System.out.println(tagid.getUserid());
//            }
//        }
////        System.out.println(userList);
//        MailId mailId=new MailId();
//        ArrayList<String> str=new ArrayList<>();
//        for(Integer userid:userList){
////            str.add(userService.getUserById(userid).getEmail());
//        }
////        System.out.println(str.size());
////        System.out.println(str);
//        mailId.setMailidList(str);
////        System.out.println(mailId.getMailidList().size());
////        System.out.println(mailId.getMailidList());
////        System.out.println(mailId.getMailidList());
////        for(String str:mailId.getMailidList()){
////            System.out.println(str);
////        }
//
//        kafkaPublishingService.MailTagInfo(mailId);
////        System.out.println(mailId.getMailidList());
//    }
}
