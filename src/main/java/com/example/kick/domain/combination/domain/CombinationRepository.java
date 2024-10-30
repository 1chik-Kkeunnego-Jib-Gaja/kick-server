package com.example.kick.domain.combination.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Long>, CombinationRepositoryCustom {
    @Query("SELECT c FROM Combination c JOIN c.tags t WHERE t.name IN :tags")
    List<Combination> findByTags(@Param("tags") List<String> tags);

    List<Combination> findByUserId(Long userId);
}
