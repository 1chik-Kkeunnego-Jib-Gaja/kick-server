package com.example.kick.domain.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAllergyRepository extends JpaRepository<UserAllergy, Long> { }
