package com.asp.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.asp.ecommerce.entity.ProductCategory;


@RepositoryRestResource(collectionResourceRel="ProductCategory",path="product-category")
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
