package com.quinbay.BlogService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComUpdateRequest {
    int ansId;
    String content;
    int updatedBy;
}
