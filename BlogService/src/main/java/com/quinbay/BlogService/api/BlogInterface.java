package com.quinbay.BlogService.api;


import com.quinbay.BlogService.model.BlogRequest;
import com.quinbay.BlogService.model.Blogs;
import com.quinbay.BlogService.model.UpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository
public interface BlogInterface {
    Blogs addTags(BlogRequest blogs);
    Blogs getBlogsById(int blogId,int userId);
    ArrayList<Blogs> getBlogs();
    HashSet<Blogs> getBlogsfromAnswer(int ansId);
    ResponseEntity<String> updateAcceptedAnswer(int blogId,int ansId);
    List<Blogs> getBlogsByUserId(int userId);
    ArrayList<Blogs> getBlogsByTags(int tagId);
    ResponseEntity<String> updateIsReported(int blogId,int reportedBy);
    ResponseEntity<String> addViews(int blogId);
    ResponseEntity<String> updateDownvotes(int blogId,Boolean check);
    ResponseEntity<String> updateUpvotes(int blogId,Boolean check);
    ArrayList<Blogs> getReportedblogs();
    ResponseEntity<String> updateBlogs(UpdateRequest updateRequest);
    ResponseEntity deleteBlog(int blogId);
    ArrayList<Blogs> searchBlogs(String hint);
}
