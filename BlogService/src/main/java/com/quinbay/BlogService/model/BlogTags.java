package com.quinbay.BlogService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogTags {
    @Id
    @SequenceGenerator(name="blogTagSeq",sequenceName = "blogTagSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "blogTagSeq")
    @Column(name="id")
    int id;
    @Column(name="blogid")
    Integer blogid;
    @Column(name="tagid")
    Integer tagid;
    @Column(name="tagname")
    String tagname;
    @Column(name="createdatetime")
    @CreationTimestamp
    Date createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    Date updatedatetime;

    public BlogTags(int blogId,int tagId,String tagName){
        this.blogid=blogId;
        this.tagid=tagId;
        this.tagname=tagName;
    }
}
