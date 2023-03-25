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
import java.util.List;

@WebServlet(name = "IdStatisticServlet", value = "/stats_id")
public class IdStatisticServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(IdStatisticServlet.class);

    @Override
    public void init() {
        LOGGER.info("Init from {}", getServletName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Enter doGet from {})", getServletName());

        List<String> allId = Service.getInstance().getAllId();
        request.setAttribute("allId", allId);

        request.getRequestDispatcher("stats_id.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Enter doPost from {}", getServletName());
        request.setCharacterEncoding("UTF-8");

        List<String> allId = Service.getInstance().getAllId();
        request.setAttribute("allId", allId);

        String id = request.getParameter("id");
        System.out.println(id);
        Service.getInstance().getById(id).ifPresent(detail -> request.setAttribute("detail", detail));
        request.getRequestDispatcher("stats_id.jsp").forward(request, response);

    }
}
