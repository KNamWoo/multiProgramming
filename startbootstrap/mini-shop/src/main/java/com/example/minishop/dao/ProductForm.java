package com.example.minishop.dao;
import com.example.minishop.domain.Product;
import jakarta.validation.constraints.*;
/**
 * ProductForm
 * 역할
 * - 상품 등록 화면에서 입력한 값을 저장
 * - 상품 수정 화면에서 입력한 값을 저장
 * HTML 입력창의 값을 잠시 저장
 * HTML 입력 -> ProductForm -> ProductService -> Product -> DB
 */

public class ProductForm {
    // 1. 상품명
    @NotBlank(message = "상품명을 입력하세요.")
    @Size(max = 100, message = "상품명은 100자 이하로 입력하세요.")
    private String name;

    // 2. 상품 가격
    @NotNull(message = "상품 가격을 입력하세요.")
    @Min(value = 100, message = "상품 가격은 100원 이상.")
    private Integer price;

    // 3. 상품 재고 수량
    @NotNull(message = "상품 재고 수량을 입력하세요.")
    @Min(value = 0, message = "상품 재고 수량은 0 이상.")
    private Integer stock;

    // 4. 상품 설명
    @Size(max = 1000, message = "상품 설명은 1000자 이하로 입력하세요.")
    private String description;

    // 5. 상품 이미지
    private String imageUrl;

    // 6. Product -> ProductForm 변환
    public static ProductForm from(Product p) {
        ProductForm f = new ProductForm();
        f.name = p.getName();
        f.price = p.getPrice();
        f.stock = p.getStock();
        f.description = p.getDescription();
        f.imageUrl = p.getImageUrl();
        return f;
    }

    // 7. Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}