package com.example.kick.domain.combination.domain;

import static com.example.kick.domain.combination.domain.QCombination.combination;
import static com.example.kick.domain.combination.domain.QTag.tag;
import com.example.kick.domain.user.domain.type.Allergy;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CombinationRepositoryCustomImpl implements CombinationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Combination> findByExcludingAllergies(List<Allergy> allergies) {
        List<String> allergyNames = allergies.stream()
            .map(Allergy::name)
            .collect(Collectors.toList());

        return queryFactory
            .selectFrom(combination)
            .distinct()
            .where(
                JPAExpressions
                    .selectFrom(tag)
                    .where(tag.combination.eq(combination)
                        .and(tag.name.in(allergyNames)))
                    .notExists()
            )
            .fetch();
    }

    @Override
    public List<Combination> findByTags(List<String> tags) {
        return queryFactory
            .selectFrom(combination)
            .leftJoin(combination.tags, tag)
            .where(tag.name.in(tags))
            .fetch();
    }
}
