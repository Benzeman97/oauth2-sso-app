package com.benz.oauth2.sso.api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OAUTH2_USER",uniqueConstraints = {
        @UniqueConstraint(name = "name",columnNames = "NAME")
})
@Getter
@Setter
public class OAuth2User {

    @Id
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME",unique = true)
    private String name;
    @Column(name = "OAUTH2_PROVIDER",nullable = false)
    private String oauth2Provider;
    @Column(name = "ROLES",nullable = false)
    private String roles;
    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;
}
