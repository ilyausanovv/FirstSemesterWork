package ru.itis.usanov.servlets;

import ru.itis.usanov.dao.impl.UserDaoImpl;
import ru.itis.usanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "changeUsernameServlet", urlPatterns = "/changeUsername")
public class ChangeUsernameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {

        UserDaoImpl userDao = new UserDaoImpl();
        HttpSession session = req.getSession();

        String newUsername = req.getParameter("new-username");
        String oldUsername = (String) session.getAttribute("username");

        User user = userDao.getUserByName(oldUsername);

        try {
            userDao.changeUsernameById(user.getId(), newUsername);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("username", newUsername);
        resp.sendRedirect("/profile");
    }
}