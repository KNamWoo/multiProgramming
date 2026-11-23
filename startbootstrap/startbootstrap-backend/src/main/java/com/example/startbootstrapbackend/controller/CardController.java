package com.example.startbootstrapbackend.controller;

import com.example.startbootstrapbackend.model.Card;
import com.example.startbootstrapbackend.repository.CardRepository;

// Spring Web annotations
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class CardController {
    private final CardRepository cardRepository;

    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // 전체 card 조회
    @GetMapping("/api/cards")
    public List<Card> getCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/api/cards/{id}")
    public Card getCard(@PathVariable Long id) {
        return cardRepository.findById(id).orElse(null);
    }
    /*public List<Card> getCards() {
        return List.of(
                new Card("👤", "PROFILE", "개인 소개와 관심 분야, 보유 역량을 정리했습니다.", "profile.html", "프로필 보기"),
                new Card("🎓", "UNIVERSITY", "청운대학교와 대학 생활에 관한 정보를 소개합니다.", "universityInfo.html", "대학 소개 보기"),
                new Card("🧩", "PORTFOLIO", "개인 작업물과 개발 활동을 확인할 수 있습니다.", "portfolio.html", "포트폴리오 보기")
        );
    }*/
}
