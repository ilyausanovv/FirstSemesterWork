package ru.itis.usanov.servlets;

import ru.itis.usanov.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginCheckServlet", urlPatterns = "/logInCheck")
public class LoginCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        UserDaoImpl users = new UserDaoImpl();

        if (users.getUserByName(username) == null) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }
}