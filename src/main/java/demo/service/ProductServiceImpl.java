package demo.service;

import demo.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    public ProductServiceImpl() {
    }
    protected Connection getConnection(){
        Connection connection=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/products?useSSL=false", "root", "123456");
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Product> printAll() throws SQLException {
        List<Product> productList=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select *from product");
        ResultSet rs= preparedStatement.executeQuery();
        while (rs.next()){
            int id=rs.getInt("id");
            String name=rs.getString("name");
            int price=rs.getInt("price");
            productList.add(new Product(id,name,price));
        }
        return productList;
    }

    @Override
    public List<Product> printAllOrderByPrice() throws SQLException {

        List<Product> productList=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select *from product order by price desc ");
        ResultSet rs= preparedStatement.executeQuery();
        while (rs.next()){
            int id=rs.getInt("id");
            String name=rs.getString("name");
            int price=rs.getInt("price");
            productList.add(new Product(id,name,price));
        }
        return productList;
    }

    @Override
    public void add(Product product) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("insert into product(name,price) value (?,?)");
        preparedStatement.setString(1,product.getName());
        preparedStatement.setInt(2,product.getPrice());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(Product product) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("update product set name=?,price=? where id=?");
        preparedStatement.setString(1,product.getName());
        preparedStatement.setInt(2,product.getPrice());
        preparedStatement.setInt(3,product.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("delete from product where id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();

    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product=null;
        Connection connection=getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement("select *from product where id=?");
        preparedStatement.setInt(1,id);
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id1=rs.getInt("id");
            String name=rs.getString("name");
            int price=rs.getInt("price");
            product=new Product(id1,name,price);
        }
        return product;
    }

    @Override
    public List<Product> findByName(String name) throws SQLException {
       List<Product> productList=new ArrayList<>();
        Connection connection=getConnection();
        PreparedStatement preparedStatement =connection.prepareStatement("select *from product where name like ?");
        preparedStatement.setString(1,'%'+name+'%');
        ResultSet rs=preparedStatement.executeQuery();
        while (rs.next()){
            int id=rs.getInt("id");
            String name1=rs.getString("name");
            int price=rs.getInt("price");
            productList.add(new Product(id,name1,price));

        }
        return productList;
    }
}
