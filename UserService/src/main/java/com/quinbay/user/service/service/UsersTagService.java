package com.quinbay.user.service.service;


import com.quinbay.user.service.api.UserTagInterface;
import com.quinbay.user.service.kafka.KafkaPublishingService;
import com.quinbay.user.service.model.MailId;
import com.quinbay.user.service.model.UserTag;
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
    UserService userService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    KafkaPublishingService kafkaPublishingService;

    public ArrayList<UserTag> getUserTags(int userId){return userTagRepository.findByUserid(userId);}

    public UserTag add_userTag(UserTag userTag) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        restTemplate.exchange(
//                "http://localhost:8082/tag/updateNoOfIntrested?Tagid="+userTag.getTagid()+"", HttpMethod.PUT, entity, ResponseEntity.class).getBody();
        return userTagRepository.save(userTag);
    }

    @Override
    public ResponseEntity deleteUserTags(int userid,int tagid) {
//        UserTag userTag=null;
        userTagRepository.deleteByUseridAndTagid(userid,tagid);
//        System.out.println(userTag.getId());
//        if (userTag!=null) {
//            userTagRepository.deleteById(userTag.getId());
//            return new ResponseEntity("Successfully deleted",HttpStatus.OK);
//        }
//        else{return new ResponseEntity("",HttpStatus.BAD_REQUEST);}
        return null;
    }

    public void mail_service(ArrayList<Integer> tagList) {
        HashSet<Integer> userList=new HashSet<>();
//        System.out.println("inside fucntion");
//        System.out.println(tagList);
        for(Integer tagId:tagList){
//            System.out.println("Inside loop");
            ArrayList<UserTag> userlist = userTagRepository.findByTagid(tagId);
            for(UserTag tagid:userlist){
//                System.out.println("inside second");
                userList.add(tagid.getUserid());
//                System.out.println(tagid.getUserid());
            }
        }
//        System.out.println(userList);
        MailId mailId=new MailId();
        ArrayList<String> str=new ArrayList<>();
        for(Integer userid:userList){
            str.add(userService.get_user_byId(userid).getEmail());
        }
//        System.out.println(str.size());
//        System.out.println(str);
        mailId.setMailidList(str);
//        System.out.println(mailId.getMailidList().size());
//        System.out.println(mailId.getMailidList());
//        System.out.println(mailId.getMailidList());
//        for(String str:mailId.getMailidList()){
//            System.out.println(str);
//        }
//
        kafkaPublishingService.MailTagInfo(mailId);
//        System.out.println(mailId.getMailidList());
    }
}
