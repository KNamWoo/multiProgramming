package com.example.startbootstrapbackend.repository;

import com.example.startbootstrapbackend.model.PortfolioCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioCardRepository extends JpaRepository<PortfolioCard, Long> { }