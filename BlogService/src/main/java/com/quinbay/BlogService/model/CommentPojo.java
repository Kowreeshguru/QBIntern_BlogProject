package com.quinbay.BlogService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentPojo {
    String content;
    Integer commentFor;
    Integer commentedby;
}
