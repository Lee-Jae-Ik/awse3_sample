package com.aws.filetest.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * StethScopeData
 *
 * @author user
 * @version 0.1
 * @see
 * @since 2021-12-24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "stethscope_data")
public class StethScopeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "file_url")
    @NotNull
    private String url;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public StethScopeData(long id, String url, User user) {
        this.id = id;
        this.url = url;
        this.user = user;
    }
}
