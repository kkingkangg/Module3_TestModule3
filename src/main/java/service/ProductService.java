package service;

import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/testModule3?useSSL=false";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Bibeo1994";

    private static final String INSERT_PRODUCT_SQL = "insert into PRODUCT (PRODUCTNAME, PRICE, QUANTITY, COLOR, `DESC`, CATEGORYID) Values (?,?,?,?,?,?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from PRODUCT where PRODUCTID = ?";
    private static final String SELECT_ALL_PRODUCT = "select * from PRODUCT";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM PRODUCT WHERE PRODUCTID =?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE PRODUCT SET PRODUCTNAME=?, PRICE=?, QUANTITY=?, COLOR=?, `DESC`=?, CATEGORYID=? WHERE PRODUCTID=?";

    private static final String SELECT_CATEGORY_BY_ID = "select * from CATEGORY where CATEGORYID = ?";
    private static final String SELECT_CATEGORY_BY_NAME = "select * from CATEGORY where CATEGORYNAME = ?";

    public ProductService() {

    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }


    public void insertProduct(Product product) throws SQLException {
        Connection conection = getConnection();
        PreparedStatement preparedStatement = conection.prepareStatement(INSERT_PRODUCT_SQL);
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getDesc());
        preparedStatement.setInt(6, product.getCategory().getCategoryId());

        preparedStatement.executeUpdate();
    }


    public Product selectProduct(int id) {
        Product product = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("productName");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String desc = rs.getString("desc");
                int categoryId = rs.getInt("categoryId");
                Category category = findCategoryById(categoryId);

                product = new Product(id, name, price,quantity, color, desc, category);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }


    public List<Product> selectAllProduct() {
        List<Product> productList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("productId");
                String name = rs.getString("productName");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String desc = rs.getString("desc");
                int categoryId = rs.getInt("categoryId");
                Category category = findCategoryById(categoryId);

                productList.add(new Product(id, name, price, quantity, color, desc, category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }


    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDelete;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);
        preparedStatement.setInt(1, id);
        rowDelete = preparedStatement.executeUpdate() > 0;
        return rowDelete;
    }


    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);
        preparedStatement.setInt(7, product.getProductId());
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setInt(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getDesc());
        preparedStatement.setInt(6, product.getCategory().getCategoryId());
        rowUpdate = preparedStatement.executeUpdate() > 0;
        return rowUpdate;
    }


    public Category findCategoryById(int id) {
        Category category = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("categoryName");
                String status = rs.getString("categoryStatus");

                category = new Category(id, name, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }

    public Category findCategoryByName(String name) {
        Category category = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_NAME);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("categoryId"));
                String status = rs.getString("categoryStatus");

                category = new Category(id, name, status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }
}


