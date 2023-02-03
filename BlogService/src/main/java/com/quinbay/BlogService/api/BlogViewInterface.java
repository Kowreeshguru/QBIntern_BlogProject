package com.quinbay.BlogService.api;

import com.quinbay.BlogService.model.BlogView;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogViewInterface {
    Boolean addBlogView(BlogView blogView);
}
