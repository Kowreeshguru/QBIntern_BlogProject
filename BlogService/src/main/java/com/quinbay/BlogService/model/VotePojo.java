package com.quinbay.BlogService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotePojo {
    Integer votedfor;
    ParentType parenttype;
    Type type;
    int voteby;
}
