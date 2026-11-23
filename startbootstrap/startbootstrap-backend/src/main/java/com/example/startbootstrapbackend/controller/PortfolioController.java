package com.example.startbootstrapbackend.controller;

import com.example.startbootstrapbackend.model.PortfolioCard;
import com.example.startbootstrapbackend.repository.PortfolioCardRepository;

// Spring Web annotations
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class PortfolioController {
    private final PortfolioCardRepository portfolioCardRepository;

    public PortfolioController(PortfolioCardRepository portfolioCardRepository) {
        this.portfolioCardRepository = portfolioCardRepository;
    }

    // 전체 card 조회
    @GetMapping("/api/portfolio/cards")
    public List<PortfolioCard> getCards() {
        return portfolioCardRepository.findAll();
    }

    @GetMapping("/api/portfolio/cards/{id}")
    public PortfolioCard getCard(@PathVariable Long id) {
        return portfolioCardRepository.findById(id).orElse(null);
    }
}
/*
public class PortfolioController {
    @GetMapping
    public List<PortfolioCard> getCards() {
        return List.of(
                new PortfolioCard(
                        "💻",
                        "개인 포트폴리오 홈페이지",
                        "4개 페이지와 REST API를 연결한 웹사이트",
                        "HTML, CSS, JavaScript, Spring Boot를 활용한 개인 포트폴리오 웹사이트입니다. 프론트엔드에서 하드코딩된 상세 내용을 제거하고 백엔드 Card API에서 데이터를 받아 렌더링합니다.",
                        "https://github.com/KNamWoo",
                        "GitHub 저장소 보기"
                ),
                new PortfolioCard(
                        "🧩",
                        "개발 프로젝트",
                        "개인 작업물과 개발 활동",
                        "프로젝트 목적, 사용 기술, 해결한 문제와 결과를 기록할 수 있는 예시 카드입니다.",
                        null,
                        null
                ),
                new PortfolioCard(
                        "📚",
                        "프로그래밍 학습",
                        "컴퓨터공학 학습 기록",
                        "수업 및 개인 학습에서 만든 결과물을 소개하는 예시 항목입니다.",
                        null,
                        null
                )
        );
    }
}
*/