package com.quinbay.BlogService.model;
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
public class BlogView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    int id;
    @Column(name="blogid")
    Integer blogid;
    @Column(name="userid")
    Integer userid;
    @Column(name="createdatetime")
    @CreationTimestamp
    LocalDateTime createdatetime;
    @Column(name="updatedatetime")
    @UpdateTimestamp
    LocalDateTime updatedatetime;
}
