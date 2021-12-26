package ru.itis.usanov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.usanov.dao.ShoppingCartDao;
import ru.itis.usanov.helper.PostgresConnectionHelper;
import ru.itis.usanov.model.Commodity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ShoppingCartDaoImpl implements ShoppingCartDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartDaoImpl.class);

    private final Connection connection = PostgresConnectionHelper.getConnection();

    @Override
    public boolean saveCommodityToShoppingCart(String username, int commodityId) {

        String sql = "INSERT INTO user_shoppingcart(username, commodity_id) VALUES (?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, commodityId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException trowables) {
            LOGGER.warn("Failed to save commodity to shopping cart", trowables);
        }
        return false;
    }

    @Override
    public LinkedList<Commodity> getListOfCommoditiesInShoppingCartByUsername(String username) throws SQLException {

        String sql = "SELECT id, name, description, image, price FROM commodities JOIN commodity_price ON commodity_price.commodity_id = commodity.id JOIN user_shoppingcart ON user_shoppingcart.commodity_id = commodity.id WHERE username = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            return getListOfCommoditiesFromDb(preparedStatement);
        }
    }

    @Override
    public boolean cleanShoppingCartForUser(String username) throws SQLException {

        String sql = "DELETE FROM user_shoppingcart WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean deleteCommodityFromShoppingCart(int commodityId) throws SQLException {

        String sql = "DELETE FROM user_shoppingcart WHERE commodity_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, commodityId);
            preparedStatement.executeUpdate();
            return true;
        }
    }

    private LinkedList<Commodity> getListOfCommoditiesFromDb(PreparedStatement preparedStatement) throws SQLException {

        ResultSet resultSet = preparedStatement.executeQuery();
        Commodity commodity = null;

        LinkedList<Commodity> listOfCommodities = new LinkedList<>();

        while (resultSet.next()){

            commodity = new Commodity();
            commodity.setId(resultSet.getInt("id"));
            commodity.setName(resultSet.getString("name"));
            commodity.setDescription(resultSet.getString("description"));
            commodity.setImage(resultSet.getString("image"));
            commodity.setPrice(resultSet.getInt("price"));
            listOfCommodities.add(commodity);
        }

        return listOfCommodities;
    }
}
