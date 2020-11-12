package controller;

import model.Category;
import model.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/productServlet")
public class ProductServlet extends HttpServlet {
    private ProductService productSevice = new ProductService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            default:
                listProduct(request, response);
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product= productSevice.selectProduct(id);
        if(product!=null){
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/delete.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productSevice.selectProduct(id);
        RequestDispatcher dispatcher;
        if(product != null){
            try {
                productSevice.deleteProduct(id);
                dispatcher = request.getRequestDispatcher("product/delete.jsp");
                request.setAttribute("message", "Delete thanh cong");
                try {
                    dispatcher.forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }



    // Update
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productSevice.selectProduct(id);
        if(product!=null){
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String desc = request.getParameter("desc");
        String categoryName = request.getParameter("categoryName");

        Category category = productSevice.findCategoryByName(categoryName);
        Product product = new Product(id, name, price, quantity, color, desc, category);
        try {
            productSevice.updateProduct(product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
            request.setAttribute("message", "Update thanh cong");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //    Create
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addProduct (HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String desc = request.getParameter("desc");
        String categoryName = request.getParameter("categoryName");

        Category category = productSevice.findCategoryByName(categoryName);
        Product product = new Product(name, price, quantity, color, desc, category);
        try {
            productSevice.insertProduct(product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
            request.setAttribute("message", "Them thanh cong");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    // Display list - read
    private void listProduct(HttpServletRequest request, HttpServletResponse response) {
        List<Product> productsList = productSevice.selectAllProduct();
        request.setAttribute("productList", productsList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
