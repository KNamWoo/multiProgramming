package com.example.minishop.controller;
import com.example.minishop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * ProductController
 * 역할
 * - 고객의 상품 관련 요청을 처리하는 컨트롤러 클래스
 * - 화면 접속, 상품 목록 조회, 상품 검색, 상품 상세 조회 등의 요청을 처리
 * 브라우저 -> ProductController -> ProductService -> ProductRepository -> DB
 */

@Controller
public class ProductController {
    // 1. ProductService 의존성 주입
    private final ProductService productService;

    // 2. 생성자 주입
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 3. 주소 매핑
    // 사용자가 http://localhost:8080/로 접속
    @GetMapping("/")
    public String home(){
        // products.html로 이동
        return "redirect:/products";
    }

    // 4. 상품 목록 조회 및 검색
    @GetMapping("/products")
    public String list(
            @RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("products", productService.findAll(keyword));
        model.addAttribute("keyword", keyword);
        return "products/list";
    }

    // 5. 상품 상세 조회
    @GetMapping("/products/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "products/detail";
    }
}
