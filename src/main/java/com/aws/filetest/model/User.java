package com.aws.filetest.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * User
 *
 * @author user
 * @version 0.1
 * @see
 * @since 2021-12-24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "이름")
    @NotNull
    private String name;

    @Column(name = "나이")
    @NotNull
    private int age;

    @Builder
    public User(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
