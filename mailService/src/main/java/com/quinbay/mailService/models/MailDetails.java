package com.quinbay.mailService.models;


import lombok.*;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDetails {
    ArrayList<String> MailidList;
}
