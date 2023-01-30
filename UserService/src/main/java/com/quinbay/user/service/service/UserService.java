package com.quinbay.user.service.service;


import com.quinbay.user.service.api.UserInterface;
import com.quinbay.user.service.model.UserReq;
import com.quinbay.user.service.model.Users;
import com.quinbay.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserInterface {
    @Autowired
    UserRepository userRepository;

    @Override
    public Users add_user(UserReq userReq) {
        try {
            Users user = new Users();
            System.out.println(userReq.getUserName());
            System.out.println(userReq.getEmail());
            System.out.println(userReq.getJobPosition());
            user.setUsername(userReq.getUserName());
            user.setPassword(userReq.getPassword());
            user.setEmail(userReq.getEmail());
            user.setJobposition(userReq.getJobPosition());
            return userRepository.save(user);
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Users get_user_byId(int userid){
        try {
            return userRepository.findByUseridAndIsdelete(userid,false);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ResponseEntity<String> update_user(int id, String userName,String emailId,String jobDescription){
        try {
            Users user = userRepository.findById(id);
            user.setUsername(userName);
            user.setEmail(emailId);
            user.setJobposition(jobDescription);
            userRepository.save(user);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<String> update_creditPoints(int userId,Boolean check){
        try {
            Users users = userRepository.findById(userId);
            if(check){users.setCreditpoints(users.getCreditpoints()+1);}
            else{users.setCreditpoints(users.getCreditpoints()-1);}
            userRepository.save(users);
            return new ResponseEntity("Successfully update",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update_password(int id, String password){
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
    public ResponseEntity<Users> user_login(String email,String password){
        Users user=null;
        try {
            user=userRepository.findByEmailAndPasswordAndIsdelete(email,password,false);
            if(user!= null){
                return new ResponseEntity(user,HttpStatus.OK);
            }else{
                return new ResponseEntity("Not updated, ID not found",HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ResponseEntity delete_user(int userId){
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
}

