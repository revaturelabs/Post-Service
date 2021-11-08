package com.reverse.postservice.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "post_images")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostImagesDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "post_id")
    private int postId;

    @Column(name = "image_location", nullable = false)
    private Integer imageLocation;

    @Column(name = "image_name", nullable = false, length = 100)
    private String imageName;

    @Column(name = "image_title", length = 50)
    private String imageTitle;
}
