package ru.itis.usanov.servlets;

import ru.itis.usanov.dao.impl.ShoppingCartDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addToShoppingCart")
public class AddToShoppingCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int id = Integer.parseInt(req.getParameter("artId"));
        String username = (String) session.getAttribute("username");

        ShoppingCartDaoImpl shoppingCartDb = new ShoppingCartDaoImpl();
        shoppingCartDb.saveCommodityToShoppingCart(username, id);

        resp.sendRedirect("/shoppingcartpage");
    }
}