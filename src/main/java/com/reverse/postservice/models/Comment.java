package com.reverse.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "post_comments")
@Getter
@AllArgsConstructor
public class Comment {

    @Id
    private Integer id;

    @Column(name = "commenter_id")
    private Integer userId;

    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "message")
    private String message;
}
