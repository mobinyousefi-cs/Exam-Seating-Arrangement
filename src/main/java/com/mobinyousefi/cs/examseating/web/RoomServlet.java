package com.mobinyousefi.cs.examseating.web;

import com.mobinyousefi.cs.examseating.dao.RoomDao;
import com.mobinyousefi.cs.examseating.model.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RoomServlet", urlPatterns = "/rooms")
public class RoomServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomDao dao = getRoomDao();
        try {
            List<Room> rooms = dao.findAll();
            req.setAttribute("rooms", rooms);
            req.getRequestDispatcher("/WEB-INF/views/rooms.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Failed to load rooms", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RoomDao dao = getRoomDao();

        try {
            if ("create".equals(action)) {
                String code = req.getParameter("code");
                int capacity = Integer.parseInt(req.getParameter("capacity"));
                dao.insert(new Room(code, capacity));
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteById(id);
            }
            resp.sendRedirect(req.getContextPath() + "/rooms");
        } catch (SQLException e) {
            throw new ServletException("Failed to modify rooms", e);
        }
    }
}
