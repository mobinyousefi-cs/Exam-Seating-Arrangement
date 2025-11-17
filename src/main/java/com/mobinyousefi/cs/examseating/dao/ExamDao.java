package com.mobinyousefi.cs.examseating.dao;

import com.mobinyousefi.cs.examseating.config.DBConnectionManager;
import com.mobinyousefi.cs.examseating.model.Exam;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ExamDao extends BaseDao {

    public ExamDao(DBConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<Exam> findAll() throws SQLException {
        String sql = "SELECT id, course_code, course_name, exam_date, start_time FROM exams ORDER BY exam_date, start_time";
        List<Exam> result = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("id"));
                e.setCourseCode(rs.getString("course_code"));
                e.setCourseName(rs.getString("course_name"));
                Date date = rs.getDate("exam_date");
                Time time = rs.getTime("start_time");
                if (date != null) {
                    e.setExamDate(date.toLocalDate());
                }
                if (time != null) {
                    e.setStartTime(time.toLocalTime());
                }
                result.add(e);
            }
        }
        return result;
    }

    public void insert(Exam exam) throws SQLException {
        String sql = "INSERT INTO exams(course_code, course_name, exam_date, start_time) VALUES(?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, exam.getCourseCode());
            ps.setString(2, exam.getCourseName());
            ps.setDate(3, exam.getExamDate() != null ? Date.valueOf(exam.getExamDate()) : null);
            ps.setTime(4, exam.getStartTime() != null ? Time.valueOf(exam.getStartTime()) : null);
            ps.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM exams WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
