package com.example.startbootstrapbackend;

import com.example.startbootstrapbackend.model.Card;
import com.example.startbootstrapbackend.model.PortfolioCard;
import com.example.startbootstrapbackend.model.ProfileCard;
import com.example.startbootstrapbackend.model.UniversityCard;
import com.example.startbootstrapbackend.repository.CardRepository;
import com.example.startbootstrapbackend.repository.PortfolioCardRepository;
import com.example.startbootstrapbackend.repository.ProfileCardRepository;
import com.example.startbootstrapbackend.repository.UniversityCardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class StartbootstrapBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(StartbootstrapBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner init(
            CardRepository cardRepository,
            PortfolioCardRepository portfolioCardRepository,
            ProfileCardRepository profileCardRepository,
            UniversityCardRepository universityCardRepository
    ) {
        return args -> {
            cardRepository.saveAll(
                    List.of(
                            new Card(
                                    "👤",
                                    "PROFILE",
                                    "개인 소개와 관심 분야, 보유 역량을 정리했습니다.",
                                    "profile.html",
                                    "프로필 보기"
                            ),
                            new Card(
                                    "🎓",
                                    "UNIVERSITY",
                                    "청운대학교와 대학 생활에 관한 정보를 소개합니다.",
                                    "universityInfo.html",
                                    "대학 소개 보기"),
                            new Card(
                                    "🧩",
                                    "PORTFOLIO",
                                    "개인 작업물과 개발 활동을 확인할 수 있습니다.",
                                    "portfolio.html",
                                    "포트폴리오 보기")
                    )
            );

            portfolioCardRepository.saveAll(
                    List.of(
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
                    )
            );

            profileCardRepository.saveAll(
                    List.of(
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
                    )
            );

            universityCardRepository.saveAll(
                    List.of(
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
                    )
            );
        };
    }
}