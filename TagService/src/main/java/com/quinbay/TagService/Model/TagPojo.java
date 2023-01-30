package com.quinbay.TagService.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagPojo {
    String tagName;
    String tagDescription;
    int createdby;
}
