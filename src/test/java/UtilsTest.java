import by.bsuir.wtlab2.logic.impl.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Mockito.*;

class UtilsTest {
    @Test
    void testUpdateFilmRating() throws Exception {
        Connection connection = mock(Connection.class);
        PreparedStatement calculateAverageStatement = mock(PreparedStatement.class);
        PreparedStatement updateAvgRatingStatement = mock(PreparedStatement.class);
        ResultSet avgRatingResult = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(calculateAverageStatement, updateAvgRatingStatement);
        when(calculateAverageStatement.executeQuery()).thenReturn(avgRatingResult);
        when(avgRatingResult.next()).thenReturn(true);
        when(avgRatingResult.getDouble("avg_rating")).thenReturn(4.5);

        Utils.updateFilmRating(1, connection);

        verify(connection, times(2)).prepareStatement(anyString());
        verify(calculateAverageStatement).setInt(1, 1);
        verify(calculateAverageStatement).executeQuery();
        verify(avgRatingResult).next();
        verify(avgRatingResult).getDouble("avg_rating");
        verify(updateAvgRatingStatement).setDouble(1, 4.5);
        verify(updateAvgRatingStatement).setInt(2, 1);
        verify(updateAvgRatingStatement).executeUpdate();

        InOrder inOrder = Mockito.inOrder(connection, calculateAverageStatement, avgRatingResult, updateAvgRatingStatement);
        inOrder.verify(connection).prepareStatement("SELECT AVG(r_rating) AS avg_rating FROM reviews WHERE r_film_id = ?");
        inOrder.verify(calculateAverageStatement).setInt(1, 1);
        inOrder.verify(calculateAverageStatement).executeQuery();
        inOrder.verify(avgRatingResult).next();
        inOrder.verify(avgRatingResult).getDouble("avg_rating");
        inOrder.verify(connection).prepareStatement("UPDATE films SET f_rating = ? WHERE f_id = ?");
        inOrder.verify(updateAvgRatingStatement).setDouble(1, 4.5);
        inOrder.verify(updateAvgRatingStatement).setInt(2, 1);
        inOrder.verify(updateAvgRatingStatement).executeUpdate();
    }
}