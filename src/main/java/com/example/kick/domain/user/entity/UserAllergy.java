package com.example.kick.domain.user.entity;

import com.example.kick.domain.user.entity.type.Allergy;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "user_allergy")
@Entity
@NoArgsConstructor
public class UserAllergy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @Enumerated(EnumType.STRING)
    private Allergy allergy;

    @Builder
    public UserAllergy(User user, Allergy allergy) {
        this.user = user;
        this.allergy = allergy;
    }
}
