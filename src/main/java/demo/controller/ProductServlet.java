package demo.controller;

import ak88.model.Student;
import demo.model.Product;
import demo.service.ProductService;
import demo.service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreate(request, response);
                break;
            case "delete":
                try {
                    deleteProduct(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    showEdit(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "sort":
                try {
                    showSort(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                try {
                    showList(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }


    }

    private void showSort(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String key=request.getParameter("key");
        if(key==null){
            List<Product> productList = productService.printAllOrderByPrice();
            request.setAttribute("productList", productList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
            requestDispatcher.forward(request, response);
        }else {
            List<Product> productList = productService.findByName(key);
            request.setAttribute("productList", productList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("product", product);
        requestDispatcher.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        String name = request.getParameter("name");
        Product product = new Product(id, name, price);
        productService.edit(product);
        response.sendRedirect("/products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.delete(id);
        response.sendRedirect("/products");
    }


    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
       String key=request.getParameter("key");
       if(key==null){
           List<Product> productList = productService.printAll();
           request.setAttribute("productList", productList);
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
           requestDispatcher.forward(request, response);
       }else {
           List<Product> productList = productService.findByName(key);
           request.setAttribute("productList", productList);
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/list.jsp");
           requestDispatcher.forward(request, response);
       }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    createProduct(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    editProduct(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int price = Integer.parseInt(request.getParameter("price"));
        String name = request.getParameter("name");
        Product product = new Product(name, price);
        productService.add(product);
        response.sendRedirect("/products");

    }
}
