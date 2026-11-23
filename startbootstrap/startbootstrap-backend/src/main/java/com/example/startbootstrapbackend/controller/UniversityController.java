package com.example.startbootstrapbackend.controller;

import com.example.startbootstrapbackend.model.UniversityCard;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/university/cards")
public class UniversityController {
    @GetMapping
    public List<UniversityCard> getCards() {
        return List.of(
                new UniversityCard(1, "🎓", "청운대학교", "대학교 및 캠퍼스 정보", "청운대학교 공식 홈페이지에서 학교 정보를 확인할 수 있습니다.", "https://www.chungwoon.ac.kr", "청운대학교 홈페이지"),
                new UniversityCard(2, "💻", "컴퓨터공학과", "전공 홈페이지", "컴퓨터공학과 관련 학과 안내와 소식을 확인할 수 있습니다.", "https://ce.chungwoon.ac.kr", "컴퓨터공학과 사이트"),
                new UniversityCard(3, "🌐", "사이버강의실", "온라인 강의 및 학습", "청운대학교 사이버강의실 바로가기입니다.", "https://cyber.chungwoon.ac.kr", "사이버강의실 열기"),
                new UniversityCard(4, "📖", "대학 생활", "수업과 학습 활동", "개인적인 수업과 활동 경험을 소개하기 위한 예시 문구입니다. 실제 경험으로 바꿔 주세요.", null, null)
        );
    }
}
