package kr.co.hanbit.product.management.domain;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository {
    public Product add(Product product);
    public Product findById(Long id);
    public List<Product> findAll();
    public List<Product> findByNameContaining(String name);
    public Product update(Product product);
    public void delete(Long id);
}


