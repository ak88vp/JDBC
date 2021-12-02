package ak88.service;

import ak88.model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentService {
    List<Student> findAll() throws SQLException, ClassNotFoundException;
    Student findById(int id) throws SQLException;
    List<Student> findByName(String name) throws SQLException;
    void add(Student student) throws SQLException, ClassNotFoundException;
    void delete(int id) throws SQLException;
    void update(Student student) throws SQLException;
    List<Student> oderBy ()throws SQLException;


}
