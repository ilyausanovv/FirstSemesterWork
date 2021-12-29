package ru.itis.usanov.servlets;

import ru.itis.usanov.dao.impl.OrdersDaoImpl;
import ru.itis.usanov.dao.impl.UserDaoImpl;
import ru.itis.usanov.model.Commodity;
import ru.itis.usanov.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "profileServlet", urlPatterns = "/profile")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        UserDaoImpl users = new UserDaoImpl();
        User user = users.getUserByName(username);
        String email = user.getEmail();

        OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();
        int ordersCount = 0;

        try {
            ordersCount = ordersDaoImpl.getCountOfOrder(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Commodity lastCommodity = null;

        try {
            lastCommodity = ordersDaoImpl.getLastOrder(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("ordersCount", ordersCount);

        if (lastCommodity == null) {
            req.setAttribute("lastProductName", "-");
        } else {
            req.setAttribute("lastProductName", lastCommodity.getName());
        }

        if (email == null) {
            req.setAttribute("email", "-");
        } else {
            req.setAttribute("email", email);
        }

        req.getServletContext().getRequestDispatcher("/profilepage.ftl").forward(req, resp);
    }

}