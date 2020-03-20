package com.code.i.repository.api;

import com.code.i.model.ProductCategory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, UUID> {

}