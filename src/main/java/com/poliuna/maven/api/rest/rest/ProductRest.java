/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poliuna.maven.api.rest.rest;

import com.poliuna.maven.api.rest.entity.Category;
import com.poliuna.maven.api.rest.entity.Product;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import com.poliuna.maven.api.rest.repository.ProductImplDAO;
import com.poliuna.maven.api.rest.service.ProductService;

/**
 *
 * @author Pc
 */
@Path("/pmcs")
public class ProductRest {

    private final ProductService productService = new ProductService(new ProductImplDAO());

    @GET
    @Path("/products")
    @Produces("application/json")
    public ArrayList<Product> getProducts() throws Exception {
        ArrayList<Product> products = (ArrayList) productService.getAll();
        return products;
    }

    @GET
    @Path("/products/{id}")
    @Produces("application/json")
    public Product getProduct(@PathParam("id") Integer id) {
        Product entity = null;
        try {
            entity = (Product) productService.findById(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }
    
    @GET
    @Path("/categories/{id}")
    @Produces("application/json")
    public Category getCategoryById(@PathParam("id") Integer id) {
        Category entity = null;
        try {
            entity = (Category) productService.getCategoryById(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }
    
    @GET
    @Path("/categories/product/{id}")
    @Produces("application/json")
    public Category getCategoryByProductId(@PathParam("id") Integer id) {
        Category entity = null;
        try {
            entity = (Category) productService.findByProductId(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }
    
    @GET
    @Path("/categories")
    @Produces("application/json")
    public ArrayList<Category> getCategories() throws Exception {
         ArrayList<Category> categories = (ArrayList<Category>) productService.getAllCategories();
        return categories;
    }

    @POST
    @Path("/products")
    @Consumes("application/json")
    @Produces("application/json")
    public Product addProduct(Product entity) {
        try {
            productService.add(entity);
        } catch (Exception ex) {
            Logger.getLogger(ProductRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entity;
    }

    @PUT
    @Path("/products")
    @Consumes("application/json")
    public void updateProduct(Product entity) {
        try {
            productService.update(entity);
        } catch (Exception ex) {
            Logger.getLogger(ProductRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @DELETE
    @Path("/products/{id}")
    public void removeProduct(@PathParam("id") Integer id) {
        try {
            productService.delete(id);
        } catch (Exception ex) {
            Logger.getLogger(ProductRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
