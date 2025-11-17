package com.mobinyousefi.cs.examseating.web;

import com.mobinyousefi.cs.examseating.dao.ExamDao;
import com.mobinyousefi.cs.examseating.model.Exam;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "ExamServlet", urlPatterns = "/exams")
public class ExamServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExamDao dao = getExamDao();
        try {
            List<Exam> exams = dao.findAll();
            req.setAttribute("exams", exams);
            req.getRequestDispatcher("/WEB-INF/views/exams.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Failed to load exams", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ExamDao dao = getExamDao();

        try {
            if ("create".equals(action)) {
                String courseCode = req.getParameter("courseCode");
                String courseName = req.getParameter("courseName");
                LocalDate date = LocalDate.parse(req.getParameter("examDate"));
                LocalTime time = LocalTime.parse(req.getParameter("startTime"));
                dao.insert(new Exam(courseCode, courseName, date, time));
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteById(id);
            }
            resp.sendRedirect(req.getContextPath() + "/exams");
        } catch (SQLException e) {
            throw new ServletException("Failed to modify exams", e);
        }
    }
}
