package demo.service;

import demo.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> printAll() throws SQLException;
    List<Product> printAllOrderByPrice() throws SQLException;
    List<Product> findByName(String name) throws SQLException;
    void add(Product product) throws SQLException;
    void edit(Product product) throws SQLException;
    void delete(int id) throws SQLException;
    Product findById(int id) throws SQLException;

}
