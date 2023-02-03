package com.quinbay.BlogService.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogView {
    @Id
    @SequenceGenerator(name="viewSeq",sequenceName = "viewSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "viewSeq")
    @Column(name="id")
    int id;
    @Column(name="blogid")
    Integer blogid;
    @Column(name="userid")
    Integer userid;
    @Column(name="createdatetime")
    @CreationTimestamp
    Date createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    Date updatedatetime;

    public BlogView(int blogid,int userid){
        this.blogid=blogid;
        this.userid=userid;
    }
}
