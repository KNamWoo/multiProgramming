package com.example.startbootstrapbackend.controller;

import com.example.startbootstrapbackend.model.ProfileCard;
import com.example.startbootstrapbackend.repository.ProfileCardRepository;

// Spring Web annotations
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class ProfileController {
    private final ProfileCardRepository profileCardRepository;

    public ProfileController(ProfileCardRepository profileCardRepository) {
        this.profileCardRepository = profileCardRepository;
    }

    // 전체 card 조회
    @GetMapping("/api/profile/cards")
    public List<ProfileCard> getCards() {
        return profileCardRepository.findAll();
    }

    @GetMapping("/api/profile/cards/{id}")
    public ProfileCard getCard(@PathVariable Long id) {
        return profileCardRepository.findById(id).orElse(null);
    }
}

/*
public class ProfileController {
    @GetMapping
    public List<ProfileCard> getCards() {
        return List.of(
                new ProfileCard(
                        "👤",
                        "기본 프로필",
                        "김남우 · 청운대학교 컴퓨터공학과",
                        "이름: 김남우\n소속: 청운대학교 컴퓨터공학과\n관심 분야: 웹 개발 · 프로그래밍 · IT 기술\nGitHub: github.com/KNamWoo",
                        "https://github.com/KNamWoo",
                        "GitHub 방문하기"
                ),
                new ProfileCard(
                        "◎",
                        "관심 분야",
                        "웹 서비스와 사용자 경험",
                        "작은 문제를 살펴보고 이해하기 쉬운 화면과 안정적인 백엔드 기능으로 풀어내는 웹 개발에 관심이 있습니다.",
                        null,
                        null
                ),
                new ProfileCard(
                        "⌘",
                        "기술 및 역량",
                        "Java · Spring Boot · HTML/CSS · JavaScript · Python",
                        "HTML, CSS, Bootstrap 5를 활용한 반응형 웹 UI 구현과 Java 및 Spring Boot 기반 REST API 연동을 학습하고 있습니다.",
                        null,
                        null
                ),
                new ProfileCard(
                        "✓",
                        "개발 목표",
                        "꾸준히 학습하고 결과물로 기록하기",
                        "수업과 개인 학습에서 익힌 개념을 직접 프로젝트로 구현하며 문제 해결 역량을 키워 나가고 있습니다.",
                        null,
                        null
                )
        );
    }
}
*/