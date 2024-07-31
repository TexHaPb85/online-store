package edu.hillel.controller;

import edu.hillel.entities.User;
import edu.hillel.repository.user.UserRepository;
import edu.hillel.repository.user.UserRepositoryInMemoryImpl;
import edu.hillel.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {
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
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setRole(User.Role.CLIENT);
        User loggedInUser;
        try {
            userService.addUser(user);
            loggedInUser = userService.logIn(user.getLogin(), user.getPassword());
            request.getSession().setAttribute("user", loggedInUser);
        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", "User already exist");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
