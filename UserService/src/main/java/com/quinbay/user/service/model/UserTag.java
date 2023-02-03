package com.quinbay.user.service.model;
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
public class UserTag {

    @Id
    @SequenceGenerator(name="usertagSeq",sequenceName = "usertagSeq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "usertagSeq")
    @Column(name="id")
    int id;
    @Column(name="userid")
    Integer userid;
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

    public UserTag(int userId,int tagId,String tagName){
        this.userid=userId;
        this.tagid=tagId;
        this.tagname=tagName;
    }
}
