package ru.itis.usanov.dao;

import ru.itis.usanov.model.Commodity;

import java.sql.SQLException;
import java.util.LinkedList;

public interface ShoppingCartDao {
    public boolean saveCommodityToShoppingCart(String username, int commodityId);
    public LinkedList<Commodity> getListOfCommoditiesInShoppingCartByUsername(String username) throws SQLException;
    public boolean cleanShoppingCartForUser(String username) throws SQLException;
    public boolean deleteCommodityFromShoppingCart(int commodityId) throws SQLException;
}
