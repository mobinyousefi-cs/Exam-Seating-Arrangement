package com.mobinyousefi.cs.examseating.dao;

import com.mobinyousefi.cs.examseating.config.DBConnectionManager;
import com.mobinyousefi.cs.examseating.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends BaseDao {

    public StudentDao(DBConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<Student> findAll() throws SQLException {
        String sql = "SELECT id, roll_number, name, program, semester FROM students ORDER BY roll_number";
        List<Student> result = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("id"));
                s.setRollNumber(rs.getString("roll_number"));
                s.setName(rs.getString("name"));
                s.setProgram(rs.getString("program"));
                s.setSemester(rs.getInt("semester"));
                result.add(s);
            }
        }
        return result;
    }

    public void insert(Student student) throws SQLException {
        String sql = "INSERT INTO students(roll_number, name, program, semester) VALUES(?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, student.getRollNumber());
            ps.setString(2, student.getName());
            ps.setString(3, student.getProgram());
            ps.setInt(4, student.getSemester());
            ps.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
