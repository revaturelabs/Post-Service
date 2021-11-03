package com.reverse.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "post_comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "commenter_id")
    private User commenter;

    @Lob
    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "created", nullable = false)
    private Instant created;

    public void setCreated(Instant created) {
        this.created = created;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
