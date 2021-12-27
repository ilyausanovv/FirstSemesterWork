package ru.itis.usanov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.usanov.dao.OrdersDao;
import ru.itis.usanov.helper.PostgresConnectionHelper;
import ru.itis.usanov.model.Commodity;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class OrdersDaoImpl implements OrdersDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(OrdersDaoImpl.class);

    private final Connection connection = PostgresConnectionHelper.getConnection();

    @Override
    public boolean insertCommodityIntoDb(String username, List<Commodity> commodities) throws SQLException {

        String sql = "INSERT INTO users_orders(username, commodity_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Commodity commodity : commodities) {
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, commodity.getId());
                preparedStatement.executeUpdate();
            }
            return true;
        }
    }

    @Override
    public List<Commodity> getLastOrderList(String username) throws SQLException {

        String sql = "SELECT id, name, description, image, price, FROM commodities JOIN commodity_price ON commodity_price.commodity_id = commodities.id JOIN users_orders ON users_orders.commodity_id = commodities.id WHERE username = ? AND order_timestamp = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Timestamp timestamp = getLastTimestamp(username);

            preparedStatement.setString(1, username);
            preparedStatement.setTimestamp(2, timestamp);

            return getListOfCommoditiesFromDb(preparedStatement);
        }
    }

    @Override
    public int getCountOfOrder(String username) throws SQLException {

        String sql = "SELECT id, name, description, image, price FROM commodities JOIN commodity_price ON commodity_price.commodity_id = commodities.id JOIN users_orders ON users_orders.commodity_id = commodity.id WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            return getListOfCommoditiesFromDb(preparedStatement).size();
        }
    }

    @Override
    public Commodity getLastOrder(String username) throws SQLException {

        if (getLastOrderList(username).isEmpty()) {
            return null;
        } else {
            return getLastOrderList(username).get(0);
        }
    }

    private Timestamp getLastTimestamp(String username) throws SQLException {

        String sql = "SELECT order_timestamp FROM users_orders WHERE username = ? ORDER BY order_timestamp DESC LIMIT 1";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            Timestamp timestamp = null;

            if (resultSet.next()) {
                timestamp = resultSet.getTimestamp("order_timestamp");
            }

            return timestamp;
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
