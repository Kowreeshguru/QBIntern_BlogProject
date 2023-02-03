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
public class Votes {
    @Id
    @SequenceGenerator(name="voteSeq",sequenceName = "voteSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "voteSeq")
    @Column(name="id")
    int id;
    @Column(name="votedfor")
    Integer votedfor;
    @Column(name="votetype")
    Boolean votetype;
    @Column(name="parenttype")
    ParentType parenttype;
    @Column(name="votedby")
    Integer votedby;
    @Column(name="createdatetime")
    @CreationTimestamp
    Date createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    Date updatedatetime;

    public Votes(int votedby,Boolean votetype,ParentType parenttype,int votedfor){
        this.votedby=votedby;
        this.votetype=votetype;
        this.parenttype=parenttype;
        this.votedfor=votedfor;
    }
}
