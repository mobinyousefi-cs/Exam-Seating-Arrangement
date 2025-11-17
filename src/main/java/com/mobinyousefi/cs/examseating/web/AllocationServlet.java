package com.mobinyousefi.cs.examseating.web;

import com.mobinyousefi.cs.examseating.dao.ExamDao;
import com.mobinyousefi.cs.examseating.dao.AllocationDao;
import com.mobinyousefi.cs.examseating.model.Allocation;
import com.mobinyousefi.cs.examseating.model.Exam;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AllocationServlet", urlPatterns = "/allocations")
public class AllocationServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExamDao examDao = getExamDao();
        AllocationDao allocationDao = getAllocationDao();

        String examIdParam = req.getParameter("examId");
        try {
            List<Exam> exams = examDao.findAll();
            req.setAttribute("exams", exams);

            if (examIdParam != null && !examIdParam.isBlank()) {
                int examId = Integer.parseInt(examIdParam);
                List<Allocation> allocations = allocationDao.findByExamId(examId);
                req.setAttribute("selectedExamId", examId);
                req.setAttribute("allocations", allocations);
            }

            req.getRequestDispatcher("/WEB-INF/views/allocations.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException("Failed to load allocations", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("generate".equals(action)) {
            int examId = Integer.parseInt(req.getParameter("examId"));
            try {
                getSeatingService().generateAndPersistAllocations(examId);
                resp.sendRedirect(req.getContextPath() + "/allocations?examId=" + examId);
            } catch (SQLException e) {
                throw new ServletException("Failed to generate allocations", e);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/allocations");
        }
    }
}
