package com.quinbay.BlogService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="blogid")
    int blogid;
    @Column(name="title")
    String title;
    @Column(name="blogdescription")
    String blogdescription;
    @Column(name="thingstried")
    String thingstried;
    @Column(name="upvotes")
    int upvotes=0;
    @Column(name="downvotes")
    int downvotes=0;
    @Column(name="quesraisedby")
    int quesraisedby=0;
    @Column(name="updateby")
    int updateby=0;
    @Column(name="noofviews")
    int noofviews=0;
    @Column(name="isclosed")
    Boolean isclosed=false;
    @Column(name="isreported")
    Boolean isreported=false;
    @Column(name="reportedby")
    int reportedby=0;
    @Column(name="createdatetime")
    @CreationTimestamp
    LocalDateTime createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    LocalDateTime updatedatetime;
    @Column(name="isdeleted")
    Boolean isdeleted=false;
}
