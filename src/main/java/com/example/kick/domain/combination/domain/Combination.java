package com.example.kick.domain.combination.domain;

import com.example.kick.domain.combination.presentation.dto.UpdateCombinationRequest;
import com.example.kick.domain.review.domain.Review;
import com.example.kick.domain.user.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "combination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "combination", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CombinationLike> likes = new ArrayList<>();

    @OneToMany(mappedBy = "combination", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Builder
    public Combination(String name, String ingredient, String recipe, String imageUrl, User user, List<Tag> tags, List<Review> reviews) {
        this.name = name;
        this.ingredient = ingredient;
        this.recipe = recipe;
        this.imageUrl = imageUrl;
        this.user = user;
        if (tags != null) {
            this.tags = tags;
        }
        this.reviews = reviews;
    }

    public void update(UpdateCombinationRequest request) {
        this.name = request.getName();
        this.ingredient = request.getIngredient();
        this.recipe = request.getRecipe();
        this.imageUrl = request.getImageUrl();
    }

    public void addTags(List<String> tagNames) {
        for (String tagName : tagNames) {
            Tag tag = Tag.builder()
                .name(tagName)
                .combination(this)
                .build();
            this.tags.add(tag);
        }
    }

    public long getLikeCount() {
        return likes.size();
    }
}

