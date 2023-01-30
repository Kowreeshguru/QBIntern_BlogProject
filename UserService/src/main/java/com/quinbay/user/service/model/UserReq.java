package com.quinbay.user.service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserReq implements Serializable {
    private String userName;
    private String password;
    private String email;
    private String jobPosition;
}
