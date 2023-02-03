package com.quinbay.TagService.Model;


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
public class Tags {
    @Id
    @SequenceGenerator(name="tagSeq",sequenceName = "tagSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tagSeq")
    @Column(name="tagid")
    int tagid;
    @Column(name="tagname")
    String tagname;
    @Column(name="tagdescription")
    String tagdescription;
    @Column(name="createdby")
    Integer createdby;
    @Column(name="updatedby")
    Integer updatedby;
    @Column(name="noofblogs")
    int noofblogs=0;
    @Column(name="noofclosedblogs")
    int noofclosedblogs=0;
    @Column(name="noofintrestedusers")
    int noofintrestedusers=0;
    @Column(name="createdatetime")
    @CreationTimestamp
    Date createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    Date updatedatetime;
    @Column(name="isdeleted")
    Boolean isdeleted=false;
}
