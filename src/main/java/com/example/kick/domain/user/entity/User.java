package com.example.kick.domain.user.entity;

import com.example.kick.domain.user.entity.type.Allergy;
import com.example.kick.domain.user.entity.type.EatingStyle;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private EatingStyle eatingStyle;

    private String goal;

    @Builder
    public User(String nickname, String password, EatingStyle eatingStyle, String goal) {
        this.nickname = nickname;
        this.password = password;
        this.eatingStyle = eatingStyle;
        this.goal = goal;
    }
}
