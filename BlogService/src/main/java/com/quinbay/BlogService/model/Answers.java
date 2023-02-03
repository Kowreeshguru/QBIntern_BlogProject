package com.quinbay.BlogService.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answers {
    @Id
    @SequenceGenerator(name="ansSeq",sequenceName = "ansSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ansSeq")
    @Column(name="ansid")
    int ansid;
    @Column(name="anscontent")
    String anscontent;
    @Column(name="answeredfor")
    Integer answeredfor;
    @Column(name="answeredby")
    Integer answeredby;
    @Column(name="upvotes")
    int upvotes=0;
    @Column(name="downvotes")
    int downvotes=0;
    @Column(name="updateby")
    Integer updateby;
//    @Column(name="isclosed")
//    Boolean isclosed=false;
    @Column(name="isreported")
    Boolean isreported=false;
    @Column(name="reportedby")
    Integer reportedby;
    @Column(name="createdatetime")
    @CreationTimestamp
    Date createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    Date updatedatetime;
    @Column(name="isdeleted")
    Boolean isdeleted=false;
}
