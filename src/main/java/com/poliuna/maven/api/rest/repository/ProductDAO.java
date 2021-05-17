/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poliuna.maven.api.rest.repository;

import com.poliuna.maven.api.rest.entity.Category;
import java.util.Collection;

/**
 *
 * @author Mauricio Machuca
 * @param <Product>
 * @param <Long>
 */
public interface ProductDAO<Product, Long> extends Repository<Product, Integer> {

    public Category findByIdCategory(Integer id);

    public Category findByProductId(Integer id);

    public Collection<Category> findAll();

}
