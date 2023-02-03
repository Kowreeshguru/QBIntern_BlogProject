package com.quinbay.BlogService.repository;

import com.quinbay.BlogService.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Integer> {
    Blogs findById(int blogId);
    ArrayList<Blogs> findByIsdeleted(Boolean deleteCheck);
    Blogs findByBlogidAndIsdeleted(int blogId,Boolean deleteCheck);
    ArrayList<Blogs>findByQuesraisedbyAndIsdeleted(int userId,Boolean deleteCheck);
    ArrayList<Blogs> findByIsreportedAndIsdeleted(Boolean check,Boolean deleteCheck);
    ArrayList<Blogs> findByIsdeletedAndTitleLikeOrBlogdescriptionLike(Boolean deleteCheck,String hint,String hint1);
    ArrayList<Blogs>findByBlogtagsTagid(int tagid);
    HashSet<Blogs> findByAnswersAnsweredby(int userId);
}
