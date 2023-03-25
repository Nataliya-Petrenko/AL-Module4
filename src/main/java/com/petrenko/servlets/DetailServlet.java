package com.petrenko.servlets;

import com.petrenko.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DetailServlet", value = "/start")
public class DetailServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetailServlet.class);

    @Override
    public void init() {
        LOGGER.info("Init from {}", getServletName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Enter doGet from {}", getServletName());
        request.getRequestDispatcher("start.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Enter doPost from {}", getServletName());
        request.setCharacterEncoding("UTF-8");
        Service.getInstance().create();
        request.getRequestDispatcher("start.jsp").forward(request, response);
    }
}
