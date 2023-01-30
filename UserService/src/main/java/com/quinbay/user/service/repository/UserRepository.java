package com.quinbay.user.service.repository;

import com.quinbay.user.service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findById(int in);
    Users findByUseridAndIsdelete(int id,Boolean flag);
    Users findByEmailAndPasswordAndIsdelete(String email,String password,Boolean check);
}
