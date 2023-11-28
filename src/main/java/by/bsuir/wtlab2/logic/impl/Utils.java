package by.bsuir.wtlab2.logic.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Class of deleting review command
 * @author haidukevgen
 * @version 1.0
 */
public class Utils {
    public static void updateFilmRating(int filmId, Connection connection) {
        try {
            PreparedStatement calculateAverageStatement = connection.prepareStatement(
                    "SELECT AVG(r_rating) AS avg_rating FROM reviews WHERE r_film_id = ?"
            );
            calculateAverageStatement.setInt(1, filmId);
            ResultSet avgRatingResult = calculateAverageStatement.executeQuery();

            double newAvgRating = 0.0;
            if (avgRatingResult.next()) {
                newAvgRating = avgRatingResult.getDouble("avg_rating");
            }

            PreparedStatement updateAvgRatingStatement = connection.prepareStatement(
                    "UPDATE films SET f_rating = ? WHERE f_id = ?"
            );
            updateAvgRatingStatement.setDouble(1, newAvgRating);
            updateAvgRatingStatement.setInt(2, filmId);
            updateAvgRatingStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
