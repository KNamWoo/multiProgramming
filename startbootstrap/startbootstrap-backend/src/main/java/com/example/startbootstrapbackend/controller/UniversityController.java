package com.example.startbootstrapbackend.controller;

import com.example.startbootstrapbackend.model.UniversityCard;
import com.example.startbootstrapbackend.repository.UniversityCardRepository;

// Spring Web annotations
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class UniversityController {
    private final UniversityCardRepository universityCardRepository;

    public UniversityController(UniversityCardRepository universityCardRepository) {
        this.universityCardRepository = universityCardRepository;
    }

    // 전체 card 조회
    @GetMapping("/api/university/cards")
    public List<UniversityCard> getCards() {
        return universityCardRepository.findAll();
    }

    @GetMapping("/api/university/cards/{id}")
    public UniversityCard getCard(@PathVariable Long id) {
        return universityCardRepository.findById(id).orElse(null);
    }
}

/*
public class UniversityController {
    @GetMapping
    public List<UniversityCard> getCards() {
        return List.of(
                new UniversityCard(
                        "🎓",
                        "청운대학교",
                        "대학교 공식 홈페이지",
                        "재학 기간: 2023.03.02 ~ 2027.02.28\n청운대학교 공식 홈페이지에서 학교 안내와 공지사항을 확인할 수 있습니다.",
                        "https://www.chungwoon.ac.kr",
                        "청운대학교 홈페이지"
                ),
                new UniversityCard(
                        "💻",
                        "컴퓨터공학과",
                        "전공 학과 홈페이지",
                        "소속: 청운대학교 컴퓨터공학과\n프로그래밍, 소프트웨어 개발, 컴퓨터 시스템 등 전공 교과와 학과 소식을 확인할 수 있습니다.",
                        "https://ce.chungwoon.ac.kr",
                        "컴퓨터공학과 사이트"
                ),
                new UniversityCard(
                        "🖥️",
                        "사이버강의실",
                        "온라인 강의 및 학습 시스템",
                        "강의 자료 확인, 과제 제출 등 온라인 수업에 이용하는 청운대학교 사이버강의실입니다.",
                        "https://cyber.chungwoon.ac.kr",
                        "사이버강의실 열기"
                ),
                new UniversityCard(
                        "📖",
                        "대학 생활과 학습",
                        "전공 수업 및 실습 프로젝트",
                        "수업에서 배운 이론을 실제 웹 애플리케이션과 프로그램으로 구현하며 실무 기초 역량을 쌓고 있습니다.",
                        null,
                        null
                )
        );
    }
}
*/