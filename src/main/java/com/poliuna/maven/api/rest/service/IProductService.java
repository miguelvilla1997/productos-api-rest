/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poliuna.maven.api.rest.service;

import com.poliuna.maven.api.rest.entity.Category;
import com.poliuna.maven.api.rest.entity.Product;
import java.util.Collection;

/**
 *
 * @author Pc
 */
public interface IProductService {
    /**
     *
     * @param product
     * @throws Exception
     */
    public void add(Product product) throws Exception;

    /**
     *
     * @param product
     * @throws Exception
     */
    public void update(Product product) throws Exception;

    /**
     *
     * @param id
     * @throws Exception
     */
    public void delete(Integer id) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Product findById(Integer id) throws Exception;
    
    public Collection<Product> getAll() throws Exception;
    
    public Category getCategoryById(Integer id) throws Exception;
    
    public Collection<Category> getAllCategories() throws Exception;
    
    public Category findByProductId(Integer id) throws Exception;

}
