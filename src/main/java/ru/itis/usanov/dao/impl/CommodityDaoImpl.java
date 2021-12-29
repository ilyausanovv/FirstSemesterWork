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

        String sql = "SELECT id, name, description, image, price, count_of_grades, rating FROM commodities JOIN commodity_rating ON commodity_id = id WHERE id = ?";

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

    public boolean setGrade(int mark, int productId) throws SQLException {

        String sql = "SELECT id, name, description, image, price, count_of_grades, rating FROM commodities JOIN commodity_rating ON commodity_id = id WHERE id = ?";
        String sqlTwo = "UPDATE commodity_rating SET count_of_grades = ?, rating = ? WHERE commodity_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);
            Commodity commodity = getCommodityFromDb(preparedStatement);
            double currentRating = commodity.getRating();
            int currentCountOfMark = commodity.getCountOfGrade();
            try (PreparedStatement preparedStatementForRating = connection.prepareStatement(sqlTwo)) {
                preparedStatementForRating.setInt(1, currentCountOfMark + 1);
                preparedStatementForRating.setDouble(2, (currentRating*currentCountOfMark + mark)/(currentCountOfMark + 1));
                preparedStatementForRating.setInt(3, productId);
                preparedStatementForRating.executeUpdate();
            }
            return true;
        }
    }
}
