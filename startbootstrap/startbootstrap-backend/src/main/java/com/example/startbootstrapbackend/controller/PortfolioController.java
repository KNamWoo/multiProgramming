package com.example.startbootstrapbackend.controller;

import com.example.startbootstrapbackend.model.PortfolioCard;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolio/cards")
public class PortfolioController {
    @GetMapping
    public List<PortfolioCard> getCards() {
        return List.of(
                new PortfolioCard(1, "💻", "개인 포트폴리오 홈페이지", "4개 페이지와 페이지별 REST API를 연결한 웹사이트", "HTML, CSS, JavaScript, Spring Boot를 활용한 예시 프로젝트입니다. 실제 작업 기간과 맡은 역할, 주요 기능을 덧붙여 주세요.", null, null),
                new PortfolioCard(2, "🧩", "개발 프로젝트", "개인 작업물과 개발 활동", "프로젝트 목적, 사용 기술, 해결한 문제와 결과를 기록할 수 있는 예시 카드입니다.", null, null),
                new PortfolioCard(3, "📚", "프로그래밍 학습", "컴퓨터공학 학습 기록", "수업 및 개인 학습에서 만든 결과물을 소개하는 예시 항목입니다.", null, null)
        );
    }
}
