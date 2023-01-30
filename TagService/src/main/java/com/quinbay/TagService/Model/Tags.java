package com.quinbay.TagService.Model;


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
public class Tags {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tagid")
    int tagid;
    @Column(name="tagname")
    String tagname;
    @Column(name="tagdescription")
    String tagdescription;
    @Column(name="createdby")
    int createdby;
    @Column(name="updatedby")
    int updatedby=0;
    @Column(name="noofblogs")
    int noofblogs=0;
    @Column(name="noofclosedblogs")
    int noofclosedblogs=0;
    @Column(name="noofintrestedusers")
    int noofintrestedusers=0;
    @Column(name="createdatetime")
    @CreationTimestamp
    LocalDateTime createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    LocalDateTime updatedatetime;
    @Column(name="isdeleted")
    Boolean isdeleted=false;
}
