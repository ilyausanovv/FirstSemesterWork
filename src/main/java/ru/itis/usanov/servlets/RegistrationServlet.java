package ru.itis.usanov.servlets;

import ru.itis.usanov.dao.impl.UserDaoImpl;
import ru.itis.usanov.helper.CookieHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "registrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/registration.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String cookieCheck = req.getParameter("remember");
        HttpSession session = req.getSession();

        UserDaoImpl users = new UserDaoImpl();
        if (users.userIsExist(username, password)) {
            resp.sendRedirect("/registration");
        } else {
            if (users.saveUser(username, password)) {
                CookieHelper.cookieCheck(resp, username, cookieCheck, session);
            }
        }
    }
}