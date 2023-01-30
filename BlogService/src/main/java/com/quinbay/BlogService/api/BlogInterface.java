package com.quinbay.BlogService.api;


import com.quinbay.BlogService.model.BlogPojo;
import com.quinbay.BlogService.model.Blogs;
import com.quinbay.BlogService.model.UpdatePojo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BlogInterface {
    Blogs add_tags(BlogPojo blogs);
    Blogs get_blogs_byId(int blogId);
    ArrayList<Blogs> get_blogs();
    ArrayList<Blogs> get_reportedblogs();
    ResponseEntity<String> update_blogs(UpdatePojo updatePojo);
    ResponseEntity delete_blog(int blogId);
    ArrayList<Blogs> searchBlogs(String hint);
}
