package ru.itis.usanov.dao;

import ru.itis.usanov.model.Commodity;

import java.sql.SQLException;
import java.util.List;

public interface OrdersDao {
    public boolean insertCommodityIntoDb(String username, List<Commodity> commodities) throws SQLException;
    public List<Commodity> getLastOrderList(String username) throws SQLException;
    public int getCountOfOrder(String username) throws SQLException;
    public Commodity getLastOrder(String username) throws SQLException;
}
