package com.reverse.postservice.models.dto;

import com.reverse.postservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "posts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FullPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "poster_id")
    private User poster;

    @Column(name = "title", length = 100)
    private String title;

    @Lob
    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "created", nullable = false)
    private Instant created;

    @Column(name = "last_edited")
    private Instant lastEdited;

    @Transient
    private Long numberOfLikes;

    @Transient
    private List<CommentDto> comments;

    @Transient
    private List<PostImagesDto> images;
}
