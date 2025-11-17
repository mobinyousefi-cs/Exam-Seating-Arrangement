package com.mobinyousefi.cs.examseating.web;

import com.mobinyousefi.cs.examseating.config.DBConnectionManager;
import com.mobinyousefi.cs.examseating.dao.*;
import com.mobinyousefi.cs.examseating.service.SeatingService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;

/**
 * Base servlet that wires up DAO and service objects from the shared
 * DBConnectionManager stored in the ServletContext.
 */
public abstract class BaseServlet extends HttpServlet {

    protected DBConnectionManager getDbManager() {
        ServletContext ctx = getServletContext();
        return (DBConnectionManager) ctx.getAttribute("DB_MANAGER");
    }

    protected StudentDao getStudentDao() {
        return new StudentDao(getDbManager());
    }

    protected RoomDao getRoomDao() {
        return new RoomDao(getDbManager());
    }

    protected ExamDao getExamDao() {
        return new ExamDao(getDbManager());
    }

    protected AllocationDao getAllocationDao() {
        return new AllocationDao(getDbManager());
    }

    protected SeatingService getSeatingService() {
        return new SeatingService(getStudentDao(), getRoomDao(), getExamDao(), getAllocationDao());
    }
}
