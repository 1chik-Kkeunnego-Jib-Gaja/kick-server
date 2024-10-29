package com.example.kick.domain.combination;

import com.example.kick.domain.combination.dto.CombinationRequest;
import com.example.kick.domain.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Entity
public class Combination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String ingredient;

    private String recipe;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder
    public Combination(String name, String ingredient, String recipe, String imageUrl, User user) {
        this.name = name;
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.imageUrl = imageUrl;
        this.user = user;
    }

    public void update(CombinationRequest request) {
        this.name = request.getName();
        this.ingredient = request.getIngredient();
        this.recipe = request.getRecipe();
        this.imageUrl = request.getImageUrl();
    }
}

