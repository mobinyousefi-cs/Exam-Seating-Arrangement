package com.mobinyousefi.cs.examseating.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application context listener responsible for initializing and cleaning up
 * shared resources such as the DB connection manager.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String url = context.getInitParameter("DB_URL");
        String username = context.getInitParameter("DB_USERNAME");
        String password = context.getInitParameter("DB_PASSWORD");

        DBConnectionManager connectionManager = new DBConnectionManager(url, username, password);
        context.setAttribute("DB_MANAGER", connectionManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Object manager = context.getAttribute("DB_MANAGER");
        if (manager instanceof DBConnectionManager dbManager) {
            dbManager.close();
        }
    }
}
