package service;

import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {
        private String jdbcURL = "jdbc:mysql://localhost:3306/testModule3?useSSL=false";
        private String jdbcUserName = "root";
        private String jdbcPassword = "Bibeo1994";

        private static final String INSERT_CATEGORY_SQL = "insert into CATEGORY (CATEGORYNAME, CATEGORYSTATUS) Values (?,?);";
        private static final String SELECT_CATEGORY_BY_ID = "select * from CATEGORY where CATEGORYID = ?";
        private static final String SELECT_ALL_CATEGORY = "select * from CATEGORY";
        private static final String DELETE_CATEGORY_SQL = "DELETE FROM CATEGORY WHERE CATEGORYID =?";
        private static final String UPDATE_CATEGORY_SQL = "UPDATE CATEGORY SET CATEGORYNAME=?, CATEGORYSTATUS=? WHERE CATEGORYID=?";

        public CategoryService() {

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


        public void insertCategory(Category category) throws SQLException {
            Connection conection = getConnection();
            PreparedStatement preparedStatement = conection.prepareStatement(INSERT_CATEGORY_SQL);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryStatus());

            preparedStatement.executeUpdate();
        }


        public Category selectCategory(int id) {
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


        public List<Category> selectAllCategory() {
            List<Category> categoryList = new ArrayList<>();
            Connection connection = getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("categoryId");
                    String name = rs.getString("categoryName");
                    String status = rs.getString("categoryStatus");
                    categoryList.add(new Category(id, name, status));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return categoryList;
        }


        public boolean deleteCategory(int id) throws SQLException {
            boolean rowDelete;
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY_SQL);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
            return rowDelete;
        }


        public boolean updateCategory(Category category) throws SQLException {
            boolean rowUpdate;
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY_SQL);
            preparedStatement.setInt(3, category.getCategoryId());
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setString(2, category.getCategoryStatus());
            rowUpdate = preparedStatement.executeUpdate() > 0;
            return rowUpdate;
        }

    }

