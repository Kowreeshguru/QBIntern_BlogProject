package com.quinbay.user.service.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @SequenceGenerator(name="userSeq",sequenceName = "userSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "userSeq")
    @Column(name="userid")
    int userid;
    @Column(name="username")
    String username;
    @JsonIgnore
    @Column(name="password")
    String password;
    @Column(name="email",unique = true)
    String email;
    @Column(name="jobposition")
    String jobposition;
    @Column(name="isadmin")
    @Type(type = "org.hibernate.type.BooleanType")
    Boolean isadmin=false;
    @Column(name="creditpoints")
    int creditpoints=0;
    @Column(name="isdelete")
    @Type(type = "org.hibernate.type.BooleanType")
    Boolean isdelete=false;
    @Column(name="createdatetime")
    @CreationTimestamp
    Date createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    Date updatedatetime;
    @OneToMany(targetEntity = UserTag.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "userid",referencedColumnName = "userid")
//    @JsonManagedReference
      List<UserTag> usertags;

}