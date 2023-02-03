package com.quinbay.TagService.Repository;

import com.quinbay.TagService.Model.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface TagRepository extends JpaRepository<Tags, Integer> {
//    Tags findById(int tagId);
    ArrayList<Tags> findByIsdeleted(Boolean deleteCheck);
}
