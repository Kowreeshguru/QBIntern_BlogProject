package com.quinbay.BlogService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnswerPojo {
    String content;
    int answeredFor;
    int answeredBy;
}
