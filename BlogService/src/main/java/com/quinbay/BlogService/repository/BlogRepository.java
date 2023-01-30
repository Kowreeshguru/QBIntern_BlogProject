package com.quinbay.BlogService.repository;

import com.quinbay.BlogService.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BlogRepository extends JpaRepository<Blogs, Integer> {
    Blogs findById(int blogId);
    ArrayList<Blogs> findByIsdeleted(Boolean check);
    Blogs findByBlogidAndIsdeleted(int blogId,Boolean check);
    ArrayList<Blogs> findByIsreportedAndIsdeleted(Boolean check,Boolean deleteCheck);
    ArrayList<Blogs> findByTitleLikeOrBlogdescriptionLike(String hint,String hint1);
}
