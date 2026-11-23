package com.example.startbootstrapbackend.model;
// JPA에서 이 클래스를 데이터베이스 테이블과 연결
import jakarta.persistence.Entity;
// 테이블의 기본키 지정
import jakarta.persistence.Id;
// ID 자동 생성 방식 지정
import jakarta.persistence.GeneratedValue;
// 클래스가 DB 테이블과 매핑될 때, ID 값을 자동으로 생성하도록 지정
import jakarta.persistence.GenerationType;

@Entity
public class ProfileCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String icon;
    private String title;
    private String description;
    private String content;
    private String link;
    private String buttonText;

    public ProfileCard() {}

    public ProfileCard(String icon, String title, String description, String content, String link, String buttonText) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.content = content;
        this.link = link;
        this.buttonText = buttonText;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
   }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}