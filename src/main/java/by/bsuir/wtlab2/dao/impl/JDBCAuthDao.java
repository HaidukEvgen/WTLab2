package by.bsuir.wtlab2.dao.impl;

import by.bsuir.wtlab2.dao.AuthDao;
import by.bsuir.wtlab2.entities.User;
import by.bsuir.wtlab2.pool.BasicConnectionPool;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class JDBCAuthDao implements AuthDao {
    private final BasicConnectionPool pool = BasicConnectionPool.getInstance();

    @Override
    public User getUser(String email, String password) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from users where u_email = ?"
            );
            preparedStatement.setString(1, email);

            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                final boolean isPasswordValid = BCrypt.checkpw(password, result.getString("u_password"));
                if (isPasswordValid) {
                    final int id = result.getInt("u_id");
                    final String name = result.getString("u_name");
                    final int rating = result.getInt("u_rating");
                    final String role = result.getString("u_role");
                    final boolean isBanned = result.getBoolean("u_banned");
                    pool.releaseConnection(connection);
                    return new User(id, name, email, rating, role, isBanned);
                } else {
                    pool.releaseConnection(connection);
                    throw new Exception("Incorrect email or password");
                }
            } else {
                pool.releaseConnection(connection);
                throw new Exception("Incorrect email or password");
            }
        } catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public int registerUser(String name, String email, String password) {
        Connection connection = pool.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into users(u_name, u_email, u_password) values(?, ?, ?)",
                    new String[]{"u_id"}
            );
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();

            int userId = 0;
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }
            pool.releaseConnection(connection);
            return userId;
        } catch (SQLException e) {
            pool.releaseConnection(connection);
            throw new RuntimeException("Email address is already used");
        }
    }
}
