package by.bsuir.wtlab2.dao.impl;

import by.bsuir.wtlab2.dao.UserDao;
import by.bsuir.wtlab2.entities.User;
import by.bsuir.wtlab2.pool.BasicConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private final BasicConnectionPool pool = BasicConnectionPool.getInstance();
    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = pool.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from users WHERE u_role = 'client' ORDER BY u_banned ASC, u_rating DESC"
            );

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                final int id = result.getInt("u_id");
                final String name = result.getString("u_name");
                final String email = result.getString("u_email");
                final int rating = result.getInt("u_rating");
                final String role = result.getString("u_role");
                final boolean isBanned = result.getBoolean("u_banned");
                users.add(new User(id, name, email, rating, role, isBanned));
            }
            pool.releaseConnection(connection);
        } catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e.getMessage());
        }
        return users;
    }

    @Override
    public void updateUser(int id, int rating, boolean isBanned) {
        Connection connection = pool.getConnection();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET u_rating = ?, u_banned = ? WHERE (u_id = ?)"
            );

            preparedStatement.setInt(1, rating);
            preparedStatement.setBoolean(2, isBanned);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

            pool.releaseConnection(connection);
        } catch (Exception e) {
            pool.releaseConnection(connection);
            throw new RuntimeException(e.getMessage());
        }
    }
}
