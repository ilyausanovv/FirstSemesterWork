package ru.itis.usanov.servlets;

import ru.itis.usanov.dao.impl.CommodityDaoImpl;
import ru.itis.usanov.dao.impl.OrdersDaoImpl;
import ru.itis.usanov.model.Commodity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/buypage")
public class BuyPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        OrdersDaoImpl ordersDaoImpl = new OrdersDaoImpl();

        List<Commodity> commodities = null;

        try {
            commodities = ordersDaoImpl.getLastOrderList(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("commodities", commodities);
        req.getServletContext().getRequestDispatcher("/buypage.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        List<Commodity> commodities = (List<Commodity>) session.getAttribute("commodities");
        CommodityDaoImpl commodityDb = new CommodityDaoImpl();

        for (Commodity commodity: commodities) {

            try {
                commodityDb.setGrade(Integer.parseInt(req.getParameter("evaluation" + commodity.getId())), commodity.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("/profile");
    }
}