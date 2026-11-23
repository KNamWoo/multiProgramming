package com.example.minishop.repository;
import com.example.minishop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 * ProductRepository 인터페이스는 Product 엔티티에 대한 데이터 액세스 작업을 수행하는 JpaRepository를 확장합니다.
 * 이 인터페이스는 Spring Data JPA가 제공하는 CRUD 및 페이징 기능을 활용할 수 있습니다.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingOrderByIdDesc(
            String keyword
    );
    List<Product> findAllByOrderByIdDesc();
}
