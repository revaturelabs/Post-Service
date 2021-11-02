package com.reverse.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
@Getter
@AllArgsConstructor
public class Post {

    @Id
    private Integer id;

    @Column(name = "poster_id")
    private Integer poster_id;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "created")
    private Timestamp createdDate;

    @Column(name = "last_edited")
    private Timestamp last_edited;
}
