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

@WebServlet(name = "editPageServlet", urlPatterns = "/editpage")
public class EditPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.getUserByName(username);

        req.getServletContext().getRequestDispatcher("/editpage.ftl").forward(req, resp);
    }
}
