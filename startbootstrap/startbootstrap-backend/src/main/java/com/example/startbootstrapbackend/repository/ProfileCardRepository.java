package com.example.startbootstrapbackend.repository;

import com.example.startbootstrapbackend.model.ProfileCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileCardRepository extends JpaRepository<ProfileCard, Long> { }