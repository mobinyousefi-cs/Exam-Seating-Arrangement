package com.mobinyousefi.cs.examseating.dao;

import com.mobinyousefi.cs.examseating.config.DBConnectionManager;
import com.mobinyousefi.cs.examseating.model.Allocation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AllocationDao extends BaseDao {

    public AllocationDao(DBConnectionManager connectionManager) {
        super(connectionManager);
    }

    public void deleteByExamId(int examId) throws SQLException {
        String sql = "DELETE FROM allocations WHERE exam_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ps.executeUpdate();
        }
    }

    public void insertAll(List<Allocation> allocations) throws SQLException {
        String sql = "INSERT INTO allocations(exam_id, student_id, room_id, seat_number) VALUES(?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Allocation a : allocations) {
                ps.setInt(1, a.getExamId());
                ps.setInt(2, a.getStudentId());
                ps.setInt(3, a.getRoomId());
                ps.setInt(4, a.getSeatNumber());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public List<Allocation> findByExamId(int examId) throws SQLException {
        String sql = "SELECT a.id, a.exam_id, a.student_id, a.room_id, a.seat_number, " +
                     "s.roll_number, s.name AS student_name, r.code AS room_code " +
                     "FROM allocations a " +
                     "JOIN students s ON a.student_id = s.id " +
                     "JOIN rooms r ON a.room_id = r.id " +
                     "WHERE a.exam_id = ? " +
                     "ORDER BY r.code, a.seat_number";

        List<Allocation> result = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Allocation a = new Allocation();
                    a.setId(rs.getInt("id"));
                    a.setExamId(rs.getInt("exam_id"));
                    a.setStudentId(rs.getInt("student_id"));
                    a.setRoomId(rs.getInt("room_id"));
                    a.setSeatNumber(rs.getInt("seat_number"));
                    a.setStudentRollNumber(rs.getString("roll_number"));
                    a.setStudentName(rs.getString("student_name"));
                    a.setRoomCode(rs.getString("room_code"));
                    result.add(a);
                }
            }
        }
        return result;
    }
}
