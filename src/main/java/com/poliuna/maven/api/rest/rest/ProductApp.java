/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poliuna.maven.api.rest.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Pc
 */
public class ProductApp extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public ProductApp() {
        singletons.add(new ProductRest());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
