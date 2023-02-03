package com.quinbay.BlogService.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Blogs {
    @Id
    @SequenceGenerator(name="blogSeq",sequenceName = "blogSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "blogSeq")
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
    Integer quesraisedby;
    @Column(name="updateby")
    Integer updateby;
    @Column(name="noofviews")
    int noofviews=0;
    @Column(name="acceptedanswer")
    Integer acceptedanswer;
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
    @OneToMany(targetEntity = Answers.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "answeredfor",referencedColumnName = "blogid")
    List<Answers> answers;
    @OneToMany(targetEntity = BlogTags.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "blogid",referencedColumnName = "blogid")
    List<BlogTags> blogtags;
}
