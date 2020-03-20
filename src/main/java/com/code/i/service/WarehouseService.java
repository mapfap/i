package com.code.i.service;

import com.code.i.model.Product;
import com.code.i.model.ProductCategory;
import com.code.i.repository.api.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final EntityManager entityManager;
    private final ProductRepository productRepository;

    public List<Product> test2() {
        return productRepository.fastSearch("vv");
    }

    @Transactional
    public void test() {
        ProductCategory category = ProductCategory.builder()
                .uuid(UUID.randomUUID())
                .name(UUID.randomUUID().toString())
                .build();

        Product product = Product.builder()
                .sku(UUID.randomUUID().toString())
                .name(UUID.randomUUID().toString())
                .category(category)
                .build();

        entityManager.persist(category);
        entityManager.persist(product);
        category.setName("JJJ");
        product.setName("Hoora444");
    }

}
