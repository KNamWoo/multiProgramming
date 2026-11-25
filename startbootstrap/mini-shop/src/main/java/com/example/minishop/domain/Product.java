package com.example.minishop.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Product 엔티티 클래스는 상품 정보를 나타내는 도메인 모델입니다.
 * 이 클래스는 JPA를 사용하여 데이터베이스 테이블과 매핑됩니다.
 */

@Entity
public class Product {
    // 1. 상품 ID: 기본키로 사용되며 자동 생성됩니다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. 상품 이름
    @Column(nullable = false, length = 100)
    private String name;

    // 3. 상품 가격
    @Column(nullable = false)
    private Integer price;

    // 4. 상품 재고 수량
    @Column(nullable = false)
    private Integer stock;

    // 5. 상품 설명
    @Column(length = 1000)
    private String description;

    // 6. 상품 이미지
    private String imageUrl;

    // 7. 상품 등록일
    private LocalDateTime createdAt;

    // 8. 생성자
    protected Product() { }

    public Product(String name, Integer price, Integer stock, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // 10. 상품 등록시간 자동 저장
    @PrePersist
    void OnCreate(){
        this.createdAt = LocalDateTime.now();
    }

    // 11. 상품 정보 수정
    public void UpdateProduct(String name, Integer price, Integer stock, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // 12. Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
