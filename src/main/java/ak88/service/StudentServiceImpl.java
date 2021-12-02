package ak88.service;

import ak88.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private static final String X = "insert into student (studentname,address,phone,status1,classid) values (?,?,?,?,?)";

    public StudentServiceImpl() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysinhvien?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public List<Student> findAll() throws SQLException, ClassNotFoundException {
        List<Student> students = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select *from student");
        System.out.println(preparedStatement);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("studentid");
            String name = rs.getString("studentname");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String stt = rs.getString("status1");
            int classId = rs.getInt("classid");
            students.add(new Student(id, name, address, phone, stt, classId));
        }
        return students;
    }

    @Override
    public Student findById(int id) throws SQLException {
        Student student = null;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select studentid,studentname,address,phone,status1,classid from student where studentid=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int studentid = resultSet.getInt("studentid");
            String name = resultSet.getString("studentname");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            String status = resultSet.getString("status1");
            int classId = resultSet.getInt("classid");
            student = new Student(studentid, name, address, phone, status, classId);
        }

        return student;
    }

    @Override
    public List<Student> findByName(String name) throws SQLException {
        List<Student> student = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select studentid,studentname,address,phone,status1,classid from student where studentname like ? ");
        preparedStatement.setString(1, '%'+name+'%');
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int studentid = resultSet.getInt("studentid");
            String name1 = resultSet.getString("studentname");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            String status = resultSet.getString("status1");
            int classId = resultSet.getInt("classid");
            student.add(new Student(studentid, name1, address, phone, status, classId));
        }
        return student;
    }

    @Override
    public void add(Student student) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(X)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getStatus());
            preparedStatement.setInt(5, student.getClassId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from student where studentid=?");) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Student student) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update student set studentname=?,address=?,phone=?,status1=?,classid=?  where studentid=?")) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getStatus());
            preparedStatement.setInt(5, student.getClassId());
            preparedStatement.setInt(6, student.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Student> oderBy() throws SQLException {
        List<Student> students=new ArrayList<>();
       Connection connection=getConnection();
       PreparedStatement preparedStatement=connection.prepareStatement("select *from student order by classid desc");

       ResultSet rs= preparedStatement.executeQuery();
       while (rs.next()){
           int id=rs.getInt("studentid");
           String name=rs.getString("studentname");
           String address=rs.getString("address");
           String phone=rs.getString("phone");
           String status=rs.getString("status1");
           int classId=rs.getInt("classid");
           students.add(new Student(id,name,address,phone,status,classId));
       }
       return students;
    }
}
