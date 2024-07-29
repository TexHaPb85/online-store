package edu.hillel.controller;

import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositoryInMemoryImpl;
import edu.hillel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.AccessException;

public class LoginServlet extends HttpServlet {
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String clientIPAddress = request.getRemoteAddr();
        User loggedInUser = userService.loggedInUser;
        try {
            userService.checkAlreadyLoggedUser(loggedInUser, clientIPAddress, userLogin);
            loggedInUser = userService.logIn(userLogin, userPassword);
            loggedInUser.setClientIPAddress(clientIPAddress);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "Invalid login or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        } catch (AccessException e) {
            request.setAttribute("errorMessage", "User already logged");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("user", loggedInUser);
        switch (loggedInUser.getRole()) {
            case CLIENT -> response.sendRedirect(request.getContextPath() + "/home");
            case ADMIN -> response.sendRedirect(request.getContextPath() + "/admin-home-page");
        }
    }
}
