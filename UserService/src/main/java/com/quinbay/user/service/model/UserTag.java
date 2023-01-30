package com.quinbay.user.service.model;
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
public class UserTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    int id;
    @Column(name="userid")
    int userid;
    @Column(name="tagid")
    int tagid;
    @Column(name="createdatetime")
    @CreationTimestamp
    LocalDateTime createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    LocalDateTime updatedatetime;
}
