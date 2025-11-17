package com.mobinyousefi.cs.examseating.web;

import com.mobinyousefi.cs.examseating.dao.StudentDao;
import com.mobinyousefi.cs.examseating.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentDao dao = getStudentDao();
        try {
            List<Student> students = dao.findAll();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/WEB-INF/views/students.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Failed to load students", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        StudentDao dao = getStudentDao();

        try {
            if ("create".equals(action)) {
                String roll = req.getParameter("rollNumber");
                String name = req.getParameter("name");
                String program = req.getParameter("program");
                int semester = Integer.parseInt(req.getParameter("semester"));
                dao.insert(new Student(roll, name, program, semester));
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteById(id);
            }
            resp.sendRedirect(req.getContextPath() + "/students");
        } catch (SQLException e) {
            throw new ServletException("Failed to modify students", e);
        }
    }
}
