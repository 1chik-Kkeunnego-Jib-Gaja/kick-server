package com.example.kick.domain.user.domain;

import com.example.kick.domain.user.domain.type.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAllergyRepository extends JpaRepository<UserAllergy, Long> {
    @Query("SELECT ua.allergy FROM UserAllergy ua WHERE ua.user.id = :userId")
    List<Allergy> findAllergiesByUserId(@Param("userId") Long userId);
}
