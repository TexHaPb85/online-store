package edu.hillel.controller;


import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositoryInMemoryImpl;
import edu.hillel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


public class StartPageServlet extends HttpServlet {
    private UserRepository userRepository;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userRepository = UserRepositoryInMemoryImpl.getSingletonInstance();
        userService = UserService.getSingletonInstance(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("contextPath", request.getContextPath());
        Map<String, HttpSession> loggedInUsers = userService.loggedInUsers;
        HttpSession currentSession = request.getSession();
        if (loggedInUsers.containsValue(currentSession)) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("startPage.jsp").forward(request, response);
        }
    }
}
