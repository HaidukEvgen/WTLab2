package by.bsuir.wtlab2.pool;

import java.sql.Connection;

/**
 * Interface of the connection pool
 * @author haidukevgen
 * @version 1.0
 */
public interface ConnectionPool {
    /**
     * Method to get connection object
     * @return Singleton connection object
     */
    Connection getConnection();

    /**
     * Method to release connection
     * @param connection Connection to release
     * @return Result of releasing
     */
    boolean releaseConnection(Connection connection);
}
