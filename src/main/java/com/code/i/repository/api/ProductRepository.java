package com.code.i.repository.api;

import com.code.i.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

    @Query(value = "SELECT p FROM Product p JOIN FETCH p.category WHERE p.sku <> :sku")
    List<Product> fastSearch(@Param("sku") String sku);
}
