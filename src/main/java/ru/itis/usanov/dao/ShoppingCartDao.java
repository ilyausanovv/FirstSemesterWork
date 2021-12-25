package ru.itis.usanov.dao;

import ru.itis.usanov.model.Commodity;

import java.util.LinkedList;

public interface ShoppingCartDao {
    public boolean saveCommodityToShoppingCart(String username, int commodityId);
    public LinkedList<Commodity> getListOfCommoditiesInShoppingCartByUsername(String username);
    public boolean cleanShoppingCartForUser(String username);
    public boolean deleteCommodityFromShoppingCart(int commodityId);
}
