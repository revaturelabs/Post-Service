package com.reverse.postservice.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class LikeId implements Serializable {

    private int postId;
    private int userId;

}
