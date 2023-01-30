package com.quinbay.BlogService.repository;

import com.quinbay.BlogService.model.BlogView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogViewRepository extends JpaRepository<BlogView, Integer> {
    BlogView findByBlogidAndUserid(int blogid,int userid);
}
