/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.poliuna.maven.api.rest.repository;

import com.poliuna.maven.api.rest.entity.Category;
import com.poliuna.maven.api.rest.entity.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import py.una.pol.par.commons.util.DBUtils;

/**
 *
 * @author Pc
 */
public class ProductImplDAO implements ProductDAO<Product, Long> {

    private static ArrayList<Product> products = new ArrayList<>();

    @Override
    public void add(Product entity) {
        try {
            Integer max = 0;
            Connection con = DBUtils.getConnection();
            String idMax = "SELECT COALESCE(MAX(p.id),0) FROM \"Products\" p; ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(idMax);
            if (rs.next()) {
                max = rs.getInt(1);
            }
            String query = "INSERT INTO public.\"Products\"(\n"
                    + "	id, name, id_category, status, precio, stock)\n"
                    + "	VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, max + 1);
            pstmt.setString(2, entity.getName());
            pstmt.setInt(3, entity.getCategory().getId());
            pstmt.setString(4, "A");
            pstmt.setDouble(5, entity.getPrecio());
            pstmt.setInt(6, entity.getStock());

            pstmt.executeUpdate();
            rs.close();
            pstmt.close();
            DBUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(Integer id) {
        try {
            Connection con = DBUtils.getConnection();
            String query = "DELETE FROM \"Products\" p WHERE p.id = ? ;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            DBUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Product entity) {
        Connection con;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            String query = "UPDATE public.\"Products\" p\n"
                    + "	SET name=?, id_category=?, status=?, precio=?, stock=?\n"
                    + "	WHERE id=?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, entity.getName());
            pstmt.setInt(2, entity.getCategory().getId());
            pstmt.setString(3, "A");
            pstmt.setDouble(4, entity.getPrecio());
            pstmt.setInt(5, entity.getStock());
            pstmt.setInt(6, entity.getId());
            pstmt.executeUpdate();
            pstmt.close();
            DBUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean contains(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product get(Integer id) {
        Connection con;
        ResultSet rs = null;
        Product p = new Product();
        try {
            con = DBUtils.getConnection();
            String query = "SELECT * FROM \"Products\" p WHERE p.id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int categoryId = rs.getInt("id_category");
                String status = rs.getString("status");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                Category category = this.findByIdCategory(categoryId);
                p.setId(id);
                p.setName(name);
                p.setCategory(category);
                p.setStatus(status);
                p.setPrecio(precio);
                p.setStock(stock);
            }
            rs.close();
            pstmt.close();
            DBUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    @Override
    public Collection<Product> getAll() {
        Connection con;
        ResultSet rs = null;
        Statement stmt = null;
        products = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String query = "SELECT * FROM public.\"Products\";";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int categoryId = rs.getInt("id_category");
                String status = rs.getString("status");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                Category category = this.findByIdCategory(categoryId);
                Product p = new Product(category, id, name, precio, stock, status);
                products.add(p);
            }

            rs.close();
            stmt.close();
            DBUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    @Override
    public boolean containsNameId(String name, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Product> findByNameId(String name, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Category findByIdCategory(Integer id) {
        Connection con;
        ResultSet rs = null;
        Category c = new Category();
        try {
            con = DBUtils.getConnection();
            String query = "SELECT id, name FROM public.\"Categories\" WHERE id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                c.setId(id);
                c.setName(name);
            }
            rs.close();
            pstmt.close();
            DBUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }

    @Override
    public Category findByProductId(Integer id) {
        Connection con;
        ResultSet rs = null;
        Category c = new Category();
        try {
            con = DBUtils.getConnection();
            String query = "SELECT id_category FROM public.\"Products\" WHERE id = ?;";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int idCategory = rs.getInt("id_category");
                c = this.findByIdCategory(idCategory);
            }
            rs.close();
            pstmt.close();
            DBUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }

    @Override
    public List<Category> findAll() {
        Connection con;
        ResultSet rs = null;
        Statement stmt = null;
        ArrayList<Category> categories = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            String query = "SELECT * FROM public.\"Categories\";";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int categoryId = rs.getInt("id");
                Category category = this.findByIdCategory(categoryId);
                categories.add(category);
            }

            rs.close();
            stmt.close();
            DBUtils.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ProductImplDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categories;
    }

}
