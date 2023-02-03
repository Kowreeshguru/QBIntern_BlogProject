package com.quinbay.BlogService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequest {
    String title;
    String description;
    String thingsTried;
    int postedBy;
    List<BlogTagRequest> tagId;
}
