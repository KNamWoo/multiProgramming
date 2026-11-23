package com.example.startbootstrapbackend.controller;

import com.example.startbootstrapbackend.model.ProfileCard;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile/cards")
public class ProfileController {
    @GetMapping
    public List<ProfileCard> getCards() {
        return List.of(
                new ProfileCard(1, "👤", "자기소개", "김남우의 개인 프로필", "이 페이지는 포트폴리오 예시 콘텐츠로 구성되어 있습니다. 실제 자기소개와 목표에 맞게 내용을 수정해 주세요.", null, null),
                new ProfileCard(2, "◎", "관심 분야", "웹 서비스와 사용자 경험", "작은 문제를 살펴보고 이해하기 쉬운 화면과 안정적인 기능으로 풀어내는 데 관심이 있습니다. 이 설명은 예시 문구입니다.", null, null),
                new ProfileCard(3, "⌘", "기술 및 역량", "Java · Spring Boot · JavaScript", "실제 학습하거나 사용한 기술과 프로젝트 경험으로 교체해 주세요.", null, null),
                new ProfileCard(4, "✓", "개발 목표", "꾸준히 학습하고 기록합니다.", "현재 공부 중인 분야와 앞으로의 목표를 작성할 수 있는 예시 항목입니다.", null, null)
        );
    }
}
