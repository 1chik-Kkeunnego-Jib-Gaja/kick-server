package com.example.kick.domain.combination.domain;

import com.example.kick.domain.user.domain.type.Allergy;

import java.util.List;

public interface CombinationRepositoryCustom {
    List<Combination> findByExcludingAllergies(List<Allergy> allergies);

    List<Combination> findByTags(List<String> tags);
}
