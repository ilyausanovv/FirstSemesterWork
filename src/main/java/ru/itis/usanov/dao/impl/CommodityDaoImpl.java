package ru.itis.usanov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.usanov.dao.CommodityDao;
import ru.itis.usanov.helper.PostgresConnectionHelper;
import ru.itis.usanov.model.Commodity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommodityDaoImpl implements CommodityDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(CommodityDaoImpl.class);

    private final Connection connection = PostgresConnectionHelper.getConnection();

    @Override
    public Commodity getCommodityById(int id) {

        String sql = "SELECT id, name, description, image, price FROM commodities JOIN commodity_price ON commodity_id = id WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, id);
            return getCommodityFromDb(preparedStatement);
        } catch (SQLException trowables) {
            LOGGER.warn("Failed execute getCommodityById", trowables);
        }
        return null;
    }

    private Commodity getCommodityFromDb(PreparedStatement preparedStatement) throws SQLException {

        ResultSet resultSet = preparedStatement.executeQuery();
        Commodity commodity = null;

        if (resultSet.next()){
            commodity = new Commodity();
            commodity.setId(resultSet.getInt("id"));
            commodity.setName(resultSet.getString("name"));
            commodity.setDescription(resultSet.getString("description"));
            commodity.setImage(resultSet.getString("image"));
            commodity.setPrice(resultSet.getInt("price"));
        }

        return commodity;
    }
}
