package com.productmanagement.dao;

import com.productmanagement.model.ProductModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductModel, Integer> {
    public List<ProductModel> findAllByOrderByPrice();

    public ProductModel findByNameContaining(String name);

    public ProductModel findById(int id);

    public List<ProductModel> findAllByOrderByName(Pageable pageable);

    public List<ProductModel> findAllByManufacturerName(String name);
}
