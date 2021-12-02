package ak88.controller;

import ak88.model.Student;
import ak88.service.StudentService;
import ak88.service.StudentServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/students")
public class StudentServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();

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
                    deleteStudent(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "view":
                try {
                    showView(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "orderBy":
                try {
                    orderBy(request,response);
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

            default: {
                try {
                    showList(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void showView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/view.jsp");
        requestDispatcher.forward(request, response);
    }

    private void findStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String name=request.getParameter("name");
        List<Student> student=studentService.findByName(name);
        request.setAttribute("student",student);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/find.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/edit.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        requestDispatcher.forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.delete(id);
        List<Student> list = studentService.findAll();
        request.setAttribute("student", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/list.jsp");
        requestDispatcher.forward(request, response);


    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/create.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/list.jsp");
        List<Student> studentList = studentService.findAll();
        request.setAttribute("student", studentList);
        requestDispatcher.forward(request, response);

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
                    createStudent(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case "find":
                try {
                    findStudent(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } break;
            case "edit":
                try {
                    editStudent(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;


        }
    }

    private void orderBy(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("student/list.jsp");
        List<Student> studentList = studentService.oderBy();
        request.setAttribute("student", studentList);

        requestDispatcher.forward(request, response);
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");
        int classId = Integer.parseInt(request.getParameter("classId"));
        Student newStudent = new Student(id, name, address, phone, status, classId);
        studentService.update(newStudent);
        response.sendRedirect("/students");
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {

        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");
        int classId = Integer.parseInt(request.getParameter("classId"));
        Student newStudent = new Student(name, address, phone, status, classId);
        studentService.add(newStudent);
        response.sendRedirect("/students");


    }
}
