package com.example.jwtlogin.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_pw", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private Role role;

    @Builder
    public User(Long id, String userId, String userName, String password, Role role) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
