package com.example.kick.domain.user.entity;

import com.example.kick.domain.user.entity.type.Allergy;
import jakarta.persistence.*;
import lombok.Builder;

@Table(name = "user_allergy")
@Entity
public class UserAllergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    private Allergy allergy;

    @Builder
    public UserAllergy(User user, Allergy allergy) {
        this.user = user;
        this.allergy = allergy;
    }
}
