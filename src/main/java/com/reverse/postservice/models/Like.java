package com.reverse.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_likes")
@Getter
@AllArgsConstructor
public class Like {

    @Column(name = "post_id")
    private int postId;

    @Column(name = "user_id")
    private int userId;
}
