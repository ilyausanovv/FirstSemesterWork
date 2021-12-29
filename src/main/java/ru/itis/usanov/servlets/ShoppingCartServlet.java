package ru.itis.usanov.servlets;

import ru.itis.usanov.dao.impl.OrdersDaoImpl;
import ru.itis.usanov.dao.impl.ShoppingCartDaoImpl;
import ru.itis.usanov.model.Commodity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/shoppingcartservlet")
public class ShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        ShoppingCartDaoImpl shoppingCartDao = new ShoppingCartDaoImpl();

        List<Commodity> commodities = null;

        try {
            commodities = shoppingCartDao.getListOfCommoditiesInShoppingCartByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("commodities", commodities);
        req.getServletContext().getRequestDispatcher("/shoppingcartpage.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        ShoppingCartDaoImpl shoppingcartDb = new ShoppingCartDaoImpl();
        OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();

        List<Commodity> commodities = new ArrayList<>();

        try {
            commodities = shoppingcartDb.getListOfCommoditiesInShoppingCartByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            ordersDaoImpl.insertCommodityIntoDb(username, commodities);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        session.setAttribute("commodities", commodities);

        try {
            shoppingcartDb.cleanShoppingCartForUser(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/buypage");
    }
}