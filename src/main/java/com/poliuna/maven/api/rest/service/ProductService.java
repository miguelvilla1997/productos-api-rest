/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poliuna.maven.api.rest.service;

import com.poliuna.maven.api.rest.entity.Category;
import com.poliuna.maven.api.rest.entity.Product;
import java.util.Collection;
import com.poliuna.maven.api.rest.repository.ProductDAO;

/**
 *
 * @author Pc
 */
public class ProductService implements IProductService {
    
    private final ProductDAO<Product, Long> productRepository;

    public ProductService(ProductDAO<Product, Long> productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public void add(Product product) throws Exception {
        productRepository.add(product);
    }

    @Override
    public void update(Product product) throws Exception {
        productRepository.update(product);
    }

    @Override
    public void delete(Integer id) throws Exception {
        productRepository.remove(id);
    }

    @Override
    public Product findById(Integer id) throws Exception {
         return productRepository.get(id);
    }

    @Override
    public Collection<Product> getAll() throws Exception {
        return productRepository.getAll();
    }

    @Override
    public Category getCategoryById(Integer id) throws Exception {
       return productRepository.findByIdCategory(id);
    }

    @Override
    public Collection<Category> getAllCategories() throws Exception {
       return productRepository.findAll();
    }

    @Override
    public Category findByProductId(Integer id) throws Exception {
      return productRepository.findByProductId(id);
    }
    
}
