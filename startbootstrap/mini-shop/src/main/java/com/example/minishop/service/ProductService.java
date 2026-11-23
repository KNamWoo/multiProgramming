package com.example.minishop.service;
import com.example.minishop.domain.Product;
import com.example.minishop.dao.ProductForm;
import com.example.minishop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * ProductService
 * 역할
 * - 상품 등록, 수정, 삭제, 조회 등의 비즈니스 로직을 처리
 * - Controller와 Repository 사이에서 데이터 처리 및 변환을 담당
 * Controller -> ProductService -> ProductRepository -> DB
 */

@Service
@Transactional(readOnly = true)
public class ProductService {
    // 1. ProductRepository 의존성 주입
    private final ProductRepository productRepository;

    // 2. 생성자 주입
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 3. 상품 조회 및 검색
    public List<Product> findAll(String keyword) {
        if(keyword == null || keyword.isBlank()){
            return productRepository.findAllByOrderByIdDesc();
        }
        return productRepository.findByNameContainingOrderByIdDesc(keyword.trim());
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("상품을 찾을 수 없습니다. id=" + id)
        );
    }

    // 4. 상품 등록
    @Transactional
    public Long create(ProductForm form) {
        Product product = new Product(
                form.getName(),
                form.getPrice(),
                form.getStock(),
                form.getDescription(),
                form.getImageUrl()
        );

        return productRepository.save(product).getId();
    }

    // 5. 상품 수정
    @Transactional
    public void update(Long id, ProductForm form) {
        Product product = findById(id);
        product.UpdateProduct(
                form.getName(),
                form.getPrice(),
                form.getStock(),
                form.getDescription(),
                form.getImageUrl()
        );
    }

    // 6. 상품 삭제
    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
