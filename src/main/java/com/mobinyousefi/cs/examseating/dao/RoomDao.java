package com.mobinyousefi.cs.examseating.dao;

import com.mobinyousefi.cs.examseating.config.DBConnectionManager;
import com.mobinyousefi.cs.examseating.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDao extends BaseDao {

    public RoomDao(DBConnectionManager connectionManager) {
        super(connectionManager);
    }

    public List<Room> findAll() throws SQLException {
        String sql = "SELECT id, code, capacity FROM rooms ORDER BY code";
        List<Room> result = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("id"));
                r.setCode(rs.getString("code"));
                r.setCapacity(rs.getInt("capacity"));
                result.add(r);
            }
        }
        return result;
    }

    public void insert(Room room) throws SQLException {
        String sql = "INSERT INTO rooms(code, capacity) VALUES(?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, room.getCode());
            ps.setInt(2, room.getCapacity());
            ps.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM rooms WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
