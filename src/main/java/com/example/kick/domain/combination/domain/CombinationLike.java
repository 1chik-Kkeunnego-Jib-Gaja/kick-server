package com.example.kick.domain.combination.domain;

import com.example.kick.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "combination_like")
@RequiredArgsConstructor
public class CombinationLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Combination combination;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public CombinationLike(Combination combination, User user) {
        this.combination = combination;
        this.user = user;
    }

}
