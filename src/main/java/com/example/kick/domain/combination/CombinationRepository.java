package com.example.kick.domain.combination;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CombinationRepository extends JpaRepository<Combination, Long> {
    List<Combination> findByUserId(Long userId);
}
