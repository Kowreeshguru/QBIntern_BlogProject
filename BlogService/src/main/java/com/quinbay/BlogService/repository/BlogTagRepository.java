package com.quinbay.BlogService.repository;

import com.quinbay.BlogService.model.BlogTags;
import com.quinbay.BlogService.model.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface BlogTagRepository extends JpaRepository<BlogTags, Integer> {
    ArrayList<BlogTags> findByBlogid(int blogid);
    ArrayList<BlogTags> findByTagid(int blogid);
    void deleteByBlogidAndTagid(int blogid, int tagid);
}
