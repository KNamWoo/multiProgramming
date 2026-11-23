package com.example.startbootstrapbackend.repository;

import com.example.startbootstrapbackend.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> { }