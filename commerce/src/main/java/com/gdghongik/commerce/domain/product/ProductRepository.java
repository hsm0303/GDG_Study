package com.gdghongik.commerce.domain.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    // TODO[W3-1]: save, findById, findAll 세 개를 정의하세요.
    //             org.springframework.* 나 jakarta.persistence.* 타입을 시그니처에 쓰면 안 됩니다.
    //             반환 타입은 Product, Optional<Product>, List<Product> 입니다.
    Product save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
