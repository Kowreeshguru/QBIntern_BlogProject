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
public class Comment {
    @Id
    @SequenceGenerator(name="comSeq",sequenceName = "comSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comSeq")
    @Column(name="commentid")
    int commentid;
    @Column(name="content")
    String content;
    @Column(name="commentedfor")
    Integer commentedfor;
    @Column(name="commentedby")
    Integer commentedby;
    @Column(name="updatedby")
    Integer updatedby;
    @Column(name="upvotes")
    int upvotes=0;
    @Column(name="downvotes")
    int downvotes=0;
    @Column(name="type")
    ParentType type;
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
