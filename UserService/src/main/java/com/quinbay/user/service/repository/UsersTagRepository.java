package com.quinbay.user.service.repository;
import com.quinbay.user.service.model.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
@Transactional
public interface UsersTagRepository extends JpaRepository<UserTag, Integer> {
    ArrayList<UserTag> findByUserid(int userid);
    ArrayList<UserTag> findByTagid(int userid);
    void deleteByUseridAndTagid(int userid, int tagid);
}
