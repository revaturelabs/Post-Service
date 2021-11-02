package com.reverse.postservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Getter
@AllArgsConstructor
public class User {

    @Id
    private Integer id;

    @Column(name = "branch")
    private Integer branch;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "date_of_birth")
    private Timestamp dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "profile_picture")
    private byte[] profilePicture;
}
