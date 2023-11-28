package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.entities.User;

import java.util.List;

/**
 * Interface of user logic dao
 * @author haidukevgen
 * @version 1.0
 */
public interface UserDao {
    /**
     * Method to get all users
     * @return List of all users
     */
    List<User> getUsers();

    /**
     * Method to update user for admin
     * @param id User ID
     * @param rating User new rating
     * @param isBanned User new ban state
     */
    void updateUser(int id, int rating, boolean isBanned);
}
