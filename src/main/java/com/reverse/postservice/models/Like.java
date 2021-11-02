package com.reverse.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_likes")
@Getter
@AllArgsConstructor
public class Like implements Serializable {

    @EmbeddedId
    private LikeId likeId;
}
