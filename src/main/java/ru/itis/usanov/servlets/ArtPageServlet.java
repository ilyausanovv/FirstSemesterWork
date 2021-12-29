package ru.itis.usanov.servlets;

import ru.itis.usanov.dao.impl.CommodityDaoImpl;
import ru.itis.usanov.model.Commodity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/artpage")
public class ArtPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        CommodityDaoImpl commodityDb = new CommodityDaoImpl();
        Commodity commodity = commodityDb.getCommodityById(id);
        req.setAttribute("art", commodity);

        req.getServletContext().getRequestDispatcher("/artpage.ftl").forward(req, resp);
    }
}