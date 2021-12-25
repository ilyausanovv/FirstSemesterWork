package ru.itis.usanov.dao;

import ru.itis.usanov.model.Commodity;

import java.util.List;

public interface OrdersDao {
    public boolean insertCommodityIntoDb(String username, List<Commodity> commodities);
    public List<Commodity> getLastOrderList(String username);
    public int getCountOfOrder(String username);
    public Commodity getLastOrder(String username);
}
