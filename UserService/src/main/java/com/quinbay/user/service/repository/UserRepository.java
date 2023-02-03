package com.quinbay.user.service.repository;

import com.quinbay.user.service.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findById(int in);
    Users findByUseridAndIsdelete(int id,Boolean deleteCheck);
    Users findByEmailAndPasswordAndIsdelete(String email,String password,Boolean deleteCheck);
    HashSet<Users> findByUsertagsTagidIn(List<Integer> tagIds);
}
