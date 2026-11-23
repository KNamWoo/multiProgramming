package com.example.startbootstrapbackend.controller;

import com.example.startbootstrapbackend.model.Card;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    @GetMapping
    public List<Card> getCards() {
        return List.of(
                new Card(1, "👤", "PROFILE", "개인 소개와 관심 분야, 보유 역량을 정리했습니다.", "profile.html", "프로필 보기"),
                new Card(2, "🎓", "UNIVERSITY", "청운대학교와 대학 생활에 관한 정보를 소개합니다.", "universityInfo.html", "대학 소개 보기"),
                new Card(3, "🧩", "PORTFOLIO", "개인 작업물과 개발 활동을 확인할 수 있습니다.", "portfolio.html", "포트폴리오 보기")
        );
    }
}
